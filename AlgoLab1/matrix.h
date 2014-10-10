// Matrix class, used to manipulate square matrices only
// Author : Gwenegan Hudin 1756642

#ifndef MATRIX_H
#define MATRIX_H

#include <iostream>
#include <vector>
#include <tuple>
#include <ctime>

using namespace std;

class Matrix
{
private:
    vector< vector<int> > elems; // 2-dim int vector to store elements

public:
	Matrix();
    Matrix(const int size);
    ~Matrix();
    vector<int>& operator [] (size_t &i); // Accessor, can be chained
    bool operator== (Matrix &mat); // Tests equality
    Matrix operator+ (Matrix &mat);
    Matrix operator- (Matrix &mat);
    size_t GetRowLength();
    size_t GetColLength(); // Redundant in square matrix
    void FillColumn(int col, int data[]); // Fills column col with integers pointed by data
    tuple<Matrix, Matrix, Matrix, Matrix> Split4(); // Splits current matrix in 4 matrices
    void Print(); // Print matrix to cout
};

#endif // MATRIX_H
