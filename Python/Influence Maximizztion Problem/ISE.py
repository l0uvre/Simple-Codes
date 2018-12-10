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
    parser.add_argument("-s", "--seed_set", help="specifies the file which defines seed set for the Influence Maximization Problem", type=argparse.FileType('r'), required=True)
    parser.add_argument("-m", "--diffusion_mode", help="specifies the diffusion mode that can be only from Independent Cascade(IC) or Linear Threshold(LT).", choices=['IC', 'LT'], required=True)
    parser.add_argument("-t", "--time_budget", help="specifies the limit of time for this run.", type=int)
    args = parser.parse_args()
    graph = None
    seed = None
    depot = -1
    capacity = -1
    with args.social_network as data:
        lines = data.readlines()
        graph = parse_network(lines) 
    with args.seed_set as data:
        lines = data.readlines()
        seed = parse_seed(lines)
    return graph, seed, args.diffusion_mode, args.time_budget

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

def parse_seed(lines):
    seed = set()
    for line in lines:
        seed.add(int(line.replace(' ', '')))
    return seed

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

def run(graph, seed_set, mode, time_budget, start_time):
    results = []
    model = None
    if mode == 'IC':
        model = IC_model
    elif mode == 'LT':
        model = LT_model
    for i in range(10000):
        results.append(model(graph, seed_set))
        if time.time() - start_time + 3.66 > time_budget:
            break
    return np.mean(results)

def main():
    start_time = time.time()
    graph, seed_set, mode, time_budget = parse_args()
    print(run(graph, seed_set, mode, time_budget, start_time))

class Graph:
    """a class corresponds to the directed graph."""
    def __init__(self, edges=None, parents=None):
        """
        edges: a dictionary, of which the key is a source, the value is another dictionary of which the key is a dest
                and the value stands for weight. e.g. {2:0.05}
        required: a list of tuples.
        """
        self.edges = edges
        self.vertices = set(self.edges.keys())
        self.parents = parents
        self.get_vertices()
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

    def get_vertices(self):
        vertices = set(self.vertices)
        for vertex in vertices:
            self.vertices = self.vertices.union(self.get_neighbors_by(vertex))
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
