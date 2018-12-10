import argparse
import random
import re
import heapq
import time
import numpy as np


def parse_args():
    """
    Parse the arguments and return values of required runtime and random seed.
    """
    parser = argparse.ArgumentParser()
    parser.add_argument("-i", "--social_network", help="social network file", type=argparse.FileType('r'), required=True)
    parser.add_argument("-k", "--seed_size", help="specifies the size of seed set", type=int, required=True)
    parser.add_argument("-m", "--diffusion_mode", help="specifies the diffusion mode that can be only from Independent Cascade(IC) or Linear Threshold(LT).", choices=['IC', 'LT'], required=True)
    parser.add_argument("-t", "--time_budget", help="specifies the limit of time for this run.", type=int)
    args = parser.parse_args()
    graph = None
    size_seed = args.seed_size
    depot = -1
    capacity = -1
    with args.social_network as data:
        lines = data.readlines()
        graph = parse_network(lines) 
    return graph, size_seed, args.diffusion_mode, args.time_budget

def parse_network(lines):
    """
    arg: lines, a list that contains the content of data.
    return the constructed graph.
    """ 
    edges = {}
    parents = {}
    pattern = re.compile(r'([0-9\.]+)\s+([0-9\.]+)\s+([0-9\.]+)\s*')
    numEdges = 0
    numNodes = 0
    for line in lines:
        match = pattern.match(line)
        if not match:
            space_pattern = re.compile(r'\s+')
            ints = space_pattern.split(line)
            numNodes = int(ints[0])
            numEdges = int(ints[1])
        else:
            src = int(match.group(1))
            dst = int(match.group(2))
            prob = float(match.group(3))
            #print(src, dst, prob)
            if src not in edges:
                edges[src] = {}
            if dst not in parents:
                parents[dst] = set()
            edges[src][dst] = prob
            parents[dst].add(src)

    graph = Graph(edges, parents)
    return graph

def IC_model(graph, seed_set):
    activatedNode = set(seed_set)
    activatedSet = set(seed_set)
    while activatedSet:
        newSet = set()
        for seed in activatedSet:
            for neighbor in graph.get_neighbors_by(seed):
                if neighbor not in activatedNode:
                    weight = graph.get_edge_weight((seed, neighbor))
                    if weight >= random.random():
                        newSet.add(neighbor)
                        activatedNode.add(neighbor)
        activatedSet = newSet
    return len(activatedNode)        

def LT_model(graph, seed_set):
    activatedNode = set(seed_set)
    activatedSet = set(seed_set)
    thresholds = graph.get_thresholds()
    while activatedSet:
        newSet = set()
        for seed in activatedSet:
            for neighbor in graph.get_neighbors_by(seed):
                if neighbor not in activatedNode:
                    parents = graph.get_parents_by(neighbor)
                    weight = 0
                    for parent in parents:
                        if parent in activatedNode:
                            weight += graph.get_edge_weight((parent, neighbor))
                    if weight >= thresholds[neighbor]:
                        newSet.add(neighbor)
                        activatedNode.add(neighbor)
        activatedSet = newSet
    return len(activatedNode)

def cal_spread(graph, seed_set, mode, time_budget, start_time):
    """
        
    """
    results = []
    model = None
    if mode == 'IC':
        model = IC_model
    elif mode == 'LT':
        model = LT_model
    for i in range(300):
        results.append(model(graph, seed_set))
        if time.time() - start_time + 2.366 > time_budget:
            break
    return np.mean(results)

def degree_discount(graph, size_seed):
    ans = set()
    degrees = {}
    degree_disc = {}
    aux = {}
    vertices = set(graph.get_vertices())
    for vertex in graph.get_vertices():
        degrees[vertex] = len(graph.get_parents_by(vertex)) + len(graph.get_neighbors_by(vertex))
        degree_disc[vertex] = degrees[vertex]
        aux[vertex] = 0

    for i in range(size_seed):
        sorted_degrees = degree_disc.values()
        max_degree = max(sorted_degrees)
        vertex = [x for x in degree_disc.keys() if degree_disc[x] == max_degree][0]
        degree_disc.pop(vertex)
        vertices.remove(vertex)
        ans.add(vertex)
        for neighbor in graph.get_neighbors_by(vertex):
            if neighbor in vertices:
                aux[neighbor] += 1
                degree_disc[neighbor] = degrees[neighbor] - 2 * aux[neighbor]\
                                       - (degrees[neighbor] - aux[neighbor]) * \
                                       aux[neighbor] * 0.8
    return ans
    
def CELFpp(seeds, graph, size_seed, mode, time_budget, start_time):
    """
    A CELF++ algorithms for finding proper seed set with a predefined size to gain a 
        maximum influence of a network graph.
       seeds: the set containing all seeds.
       graph: the graph object.
       size_seed: the size of chosen seed set.
       mode: str, "IC" or "LT"
    """
    max_spread = float('-inf')
    res = set()
    pq = [[1, -1, -1]] # node is an array, the first is the marginal value, the second is the node index and 
                        # last is the size of temporary seed set.
    # initialization
    for seed in seeds:
        tmp_set = set([seed])
        spread = cal_spread(graph, tmp_set, mode, time_budget, start_time)
        heapq.heappush(pq, [-spread, seed, 0])
    node = heapq.heappop(pq)
    res.add(node[1])
    max_spread = -node[0]

    while len(res) < size_seed:
        while True:
            tmp_set = set(res)
            node = heapq.heappop(pq)
            if node[2] == len(res):
                res.add(node[1])
                break
            tmp_set.add(node[1])
            diff_spread = cal_spread(graph, tmp_set, mode, time_budget, start_time)
            top = pq[0]
            if diff_spread - max_spread >= -top[0]:
                res.add(node[1])
                max_spread = diff_spread
                if len(res) == size_seed:
                    break
            else:
                node[0] = -(diff_spread - max_spread)
                node[2] = len(res)
                heapq.heappush(pq, node)
    return res, max_spread

def main():
    start_time = time.time()
    graph, size, mode, time_budget = parse_args()
    
    cleaned_set = degree_discount(graph, min(8*size, len(graph.get_vertices())))
    seeds, spread = CELFpp(cleaned_set, graph, size, mode, time_budget, start_time)
    for seed in seeds:
        print(seed)
class Graph:
    """a class corresponds to the directed graph."""
    def __init__(self, edges=None, parents=None):
        """
        edges: a dictionary, of which the key is a source, the value is another dictionary of which the key is a dest
                and the value stands for weight. e.g. {2:0.05}
        parents: a dictionary, of which keys are vertices, and values are sets containing all of their parent vertices.
        """
        self.edges = edges
        self.vertices = set(self.edges.keys())
        self.parents = parents
        self.cal_vertices()
        self.threshold = self.cal_thresholds()

    def get_edge_weight(self, edge):
        return self.edges[edge[0]][edge[1]]

    def get_neighbors_by(self, node):
        if node not in self.edges.keys():
            return set()
        return self.edges[node].keys()

    def get_parents_by(self, node):
        if node not in self.parents.keys():
            return set()
        return self.parents[node]

    def cal_vertices(self):
        vertices = set(self.vertices)
        for vertex in vertices:
            self.vertices = self.vertices.union(self.get_neighbors_by(vertex))
        return self.vertices

    def get_vertices(self):
        return self.vertices

    def get_thresholds(self):
        return self.threshold
    def cal_thresholds(self):
        threshold = {}
        for vertex in self.vertices:
            threshold[vertex] = random.random()
        return threshold

if __name__ == "__main__":
    main()
