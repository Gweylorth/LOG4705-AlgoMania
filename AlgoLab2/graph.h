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
    vector<int> vertices; // Vertices in graph
    vector< pair <int, int> > axis; // Axis between vertices

public:
    vector< list <int> > adjacencies; // holds, for one vertex, all adjacent vertices

    //Graph() {}
    Graph(int, vector< pair <int, int> >&);
    void operator -(int); // removes a vertex from graph
    vector<int>& GetVertices() {return this->vertices;}
    int InDegree(int);
};

#endif // GRAPH_H
