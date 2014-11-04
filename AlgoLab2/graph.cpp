#include "graph.h"

Graph::Graph(int v, vector<pair<int, int> > &a)
    : axis(a) {
    for (int i = 0; i < v; i++) {
        this->vertices.push_back(i);
    }
    this->adjacencies = vector < list <int> > (this->vertices.size());

    for (auto& ax : this->axis) {
        this->adjacencies[ax.first].push_back(ax.second);
    }
}

void Graph::operator -(int v) {
    auto it = std::find(this->vertices.begin(), this->vertices.end(), v);
    this->vertices.erase(it);
    for (list<int>& adjs : this->adjacencies) {
        adjs.remove(v);
    }
}

int Graph::InDegree(int v) {
    int count = 0;

    for (auto& adj : this->adjacencies) {
        if (std::find(adj.begin(), adj.end(), v) != adj.end()) {
            count++;
        }
    }

    return count;
}
