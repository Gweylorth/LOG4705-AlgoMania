#include "parser.h"

Parser::Parser()
{
}

Matrix Parser::Read(const std::string filePath)
{
    std::ifstream inFile (filePath);
    std::string line;
    int sizeExponent;

    std::getline(inFile, line);
    std::istringstream iss (line);
    iss >> sizeExponent;
    int size = static_cast<int>(pow(2, sizeExponent));
    Matrix matrix (size);

    size_t i = 0, j;
    while(std::getline(inFile, line) && i < size) {
        j = 0;
        iss.clear();
        iss.str(line);
        for (;j < size; j++) {
            iss.ignore(1);
            iss >> matrix[i][j];
        }
        i++;
    }

    return matrix;
}
