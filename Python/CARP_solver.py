import argparse
import re
import heapq

def parse_arg():
    """
    Parse the arguments and return values of required runtime and random seed.
    """
    parser = argparse.ArgumentParser()
    parser.add_argument("instance_file", help="the data file", type=argparse.FileType('r'))
    parser.add_argument("-t", "--termination", help="specifies the running time in seconds", type=int, required=True)
    parser.add_argument("-s", "--seed", help="specifies the random seed used in this run.", type=int, required=True)
    args = parser.parse_args()
    graph = None
    depot = -1
    capacity = -1
    with args.instance_file as data:
        lines = data.readlines()
        #print(lines)
        graph, depot, capacity = parse_line(lines)
#    print(args.termination)
#    print(args.seed)
    return args.termination, args.seed, graph, depot, capacity

def parse_line(lines):
    """
    arg: lines, a list that contains the content of data.
    return the constructed graph.
    """ 
    #pattern = re.compile(r'([^\s]+($|\s*[^\s]+))\s*:\s*([^\n]+)\n$')
    edges = {}
    pattern = re.compile(r'([\w\-\s]+)\s+:\s*([^\n]+)\n$')
    depot = -1
    capacity = -1
    required_edges = []
    for line in lines:
        match = pattern.match(line)
        if match:
            #print(match.groups())
            first = match.group(1)
            second = match.group(2)
            print(first, end=" ")
            print(second)
            if first == 'DEPOT':
                depot = int(second)
            elif first == 'CAPACITY':
                capacity = int(second) 
        else:
            space_pattern = re.compile(r'\s+')
            ints = space_pattern.split(line)
            if len(ints) >= 5:
                ints = ints[0:-1]
                ints = [int(item) for item in ints]
                if ints[0] not in edges:
                    edges[ints[0]] = {}
                if ints[1] not in edges:
                    edges[ints[1]] = {}
                edges[ints[0]][ints[1]] = [ints[2], ints[3]]
                edges[ints[1]][ints[0]] = [ints[2], ints[3]]
                print(ints)
                if ints[3] > 0:
                    required_edges.append((ints[0], ints[1]))
                    required_edges.append((ints[1], ints[0]))

    #print(required_edges)
    graph = Graph(edges, required_edges)
    '''
    for edge in required_edges:
        print(edge, end=" ")
        print(graph.get_edge_cost(edge), end=" ")
        print(graph.get_edge_demand(edge))
    '''
    return graph, depot, capacity
    

def path_scanning(graph, depot, capacity):
    '''
    TODO
    '''
    graph.cal_shortest_path()
    required = set(graph.get_required())
    print(required)
    total = 0
    routes = []
    vertex = depot
    depotCosts = graph.get_sp_by_vertex(vertex)
    while required:
        routes.append(0)
        load = 0
        while load < capacity:
            minDist = float('inf')
            edgeToServe = None
            thisLoad = 0
            costs = graph.get_sp_by_vertex(vertex)
            for rEdge in required:
                currLoad = graph.get_edge_demand(rEdge)
                if currLoad + load <= capacity:
                    if costs[rEdge[0]] < minDist:
                        minDist = costs[rEdge[0]]   
                        edgeToServe = rEdge
                        thisLoad = currLoad
            if minDist == float('inf') or not required:
                break
            routes.append(edgeToServe)
            total += minDist + graph.get_edge_cost(edgeToServe) # buggy
            required.remove(edgeToServe)
            required.remove((edgeToServe[1], edgeToServe[0]))
            load += thisLoad
            #print(edgeToServe, total, minDist, graph.get_edge_cost(edgeToServe), edgeToServe)
            vertex = edgeToServe[1]
        total += depotCosts[vertex]
        vertex = depot
        routes.append(0)
    return total, routes

def main():
    termination, seed, graph, depot, capacity = parse_arg()
    total, routes = path_scanning(graph, depot, capacity)
    print(depot, end=" ")
    print(capacity)
    print(routes)
    print(total)

class Graph:
    """a class corresponds to the undirected graph."""
    def __init__(self, edges, required):
        """
        edges: a dictionary, of which the key is a source, the value is another dictionary of which the key is a dest
                and the value is a list with cost and demand values stored. {1:{2:[2, 2]}}
        required: a list of tuples.
        """
        self.edges = edges
        self.vertices = set(self.edges.keys())
        self.sp = {} # shortest paths with all source vertices as keys .
        self.sp_cost_only = {}
        self.required_edges = required
        #print(self.vertices) 
        print(edges)

    def cal_shortest_path(self):
        """
        calculate all shortest paths in which all vertices could be the source and store
        """ 
        for vertex in self.vertices:
            cost, path = self.dijkstra(vertex)
            self.sp[vertex] = path
            self.sp_cost_only[vertex] = cost
        #print(self.sp)
        #print(self.sp_cost_only)

    def get_sp_by_vertex(self, src):
        """
        return a dict in which keys are destination and values are shortest costs.
        """
        return self.sp_cost_only[src]

    def get_edge_cost(self, edge):
        return self.edges[edge[0]][edge[1]][0]

    def get_edge_demand(self, edge):
        return self.edges[edge[0]][edge[1]][1]

    def get_required(self):
        return self.required_edges

    def dijkstra(self, src):
        """
        calculate  and return shortest paths from src vertex and corresponding distances.
        arg: src, the source.
        return a dict in which the key is the destination and the value is a list, of which the first element is a list path,
            the second element is corresonding cost.
        """
        #print(src)
        costs = {}
        prev = {}
        paths = {}
        for vertex in self.vertices:
            costs[vertex] = float('inf')
        costs[src] = 0

        pq = [[0,[src]]]
        while pq:
            node = heapq.heappop(pq)
            fringe = node[1]
            curr = fringe[-1]

            if self.edges[curr].keys():
                for neighbor in self.edges[curr].keys():
                    newDist = node[0] + self.edges[curr][neighbor][0]
                    if newDist < costs[neighbor]:
                        costs[neighbor] = newDist
                        newFringe = fringe[:]
                        newFringe.append(neighbor)
                        heapq.heappush(pq, [newDist, newFringe])
                        prev[neighbor] = curr
                        paths[neighbor] = [newFringe[:], newDist]
        #print(paths)
        #costs.pop(src, None)
        #print(costs)
        ''' 
        Another way to store the shortes path.
        path = {}
        for vertex in self.vertices:
            if vertex != src:
                l = []
                p = vertex
                while p != src:
                    l.append(p)
                    p = prev[p]
                l.append(src)
                l.reverse()
                l.append(costs[vertex])
                path[vertex] = l[:]
        print(path)
        '''
        return costs, paths

if __name__ == "__main__":
    main()
