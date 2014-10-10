#include "matrix.h"

Matrix::Matrix() {}

Matrix::Matrix(const int size)
{
    vector< vector<int> > base (size, vector<int>(size, 0));
    this->elems = base;
}

Matrix::~Matrix() {}

vector<int>& Matrix::operator [] (size_t &i)
{
    return this->elems[i];
}

bool Matrix::operator== (Matrix &mat)
{
    unsigned int size = this->elems.size();
    if (size != mat.GetRowLength()) {
        return false;
    }

    for (size_t i = 0; i < size; i++) {
        for (size_t j = 0; j < size; j++) {
            if (this->elems[i][j] != mat[i][j]){
                return false;
            }
        }
    }

    return true;
}

Matrix Matrix::operator+ (Matrix &mat)
{
	unsigned int size = this->elems.size();
	Matrix sum(size);

	for (size_t i = 0; i < size; i++) {
		for (size_t j = 0; j < size; j++) {
			sum[i][j] = this->elems[i][j] + mat[i][j];
		}
	}

	return sum;
}

Matrix Matrix::operator- (Matrix &mat)
{
	unsigned int size = this->elems.size();
	Matrix sum(size);

	for (size_t i = 0; i < size; i++) {
		for (size_t j = 0; j < size; j++) {
			sum[i][j] = this->elems[i][j] - mat[i][j];
		}
	}

	return sum;
}

size_t Matrix::GetRowLength()
{
    return this->elems.size();
}

size_t Matrix::GetColLength()
{
    return this->elems[0].size();
}

void Matrix::FillColumn(int col, int* data) {
    this->elems[col].assign(data, data + this->elems.size());
}

tuple<Matrix, Matrix, Matrix, Matrix> Matrix::Split4() 
{
	const unsigned int splitSize = this->elems.size() / 2;
	Matrix a11(splitSize),
		a12(splitSize),
		a21(splitSize),
		a22(splitSize);

	for (size_t i = 0 ; i < splitSize; i++) {
		for (size_t j = 0; j < splitSize; j++) {
			a11[i][j] = this->elems[i][j];
			a12[i][j] = this->elems[i][j + splitSize];
			a21[i][j] = this->elems[i + splitSize][j];
			a22[i][j] = this->elems[i + splitSize][j + splitSize];
		}
	}

    return tuple<Matrix, Matrix, Matrix, Matrix> (a11, a12, a21, a22);
}

void Matrix::Print()
{
    for(size_t i = 0; i < this->GetRowLength(); i++){
        for(size_t j = 0; j < this->GetColLength(); j++){
            std::cout << this->elems[i][j] << " ";
        }
        std::cout << std::endl;
    }
    std::cout << std::endl;
}
