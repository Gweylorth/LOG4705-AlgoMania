#include "parser.h"

Parser::Parser()
{
}

Graph Parser::Read(const std::string filePath)
{
    std::ifstream inFile (filePath);
    std::string line;
    int vertices;
    vector< pair <int, int> > axis;

    std::getline(inFile, line);
    std::istringstream iss (line);
    iss >> vertices;

    std::string u,v;
    while(std::getline(inFile, line)) {
        iss.clear();
        iss.str(line);
        std::getline(iss, u, ' ');
        std::getline(iss, v);
        axis.push_back(std::make_pair(stoi(u), stoi(v)));
    }

      return Graph (vertices, axis);
}
