#ifndef GRAPH_H
#define GRAPH_H

#include <iostream>
#include <vector>
#include <list>
#include <algorithm>
#include <utility>


using std::vector;
using std::pair;
using std::list;

class Graph
{
private:
    int vertices; // Vertices count in graph
    vector< pair <int, int> > axis; // Axis between vertices

public:
    vector < list <int> > adjacencies; // holds, for one vertex, all adjacent vertices

    Graph(int, vector< pair <int, int> >&);
    int GetVertices() {return this->vertices;}
    int InDegree(int);
};

#endif // GRAPH_H
