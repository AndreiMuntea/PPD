#ifndef MATRIX_HPP
#define MATRIX_HPP


#include <vector>
#include <ostream>
#include <sal.h>

template <class T>
class Matrix
{
public:
	class Row
	{
		friend class Matrix;

	public:
		T& 
		operator[] (
			_In_ size_t Column
		) 
		{ 
			return parent.matrix[row][Column]; 
		}

	private:
		Row(
			_In_ Matrix& Parent, 
			_In_ size_t Row
		) : 
			parent{ Parent },
			row{ Row } 
		{
		}

		Matrix& parent;
		size_t row;
	};

	Matrix() = delete;
	
	Matrix(
		_In_ const std::vector<std::vector<T> >& Elements
	) : 
		matrix{ Elements } 
	{
	}
	
	Matrix(
		_In_ size_t NoLines,  
		_In_ size_t NoColumns
	): 
		matrix{ std::vector<std::vector<T>>(NoLines, std::vector<T>(NoColumns)) }
	{
	}
	
	~Matrix() = default;
	
	Row 
	operator[](
		_In_ size_t Line
	)
	{
		return Row{ *this, Line };
	}
	
	inline 
	size_t 
	GetNoLines() const 
	{ 
		return matrix.size(); 
	}

	inline 
	size_t 
	GetNoColumns() const 
	{ 
		return matrix[0].size(); 
	}

	bool
	operator==(
		_In_ Matrix<T>& Other
	) 
	{
		if (Other.GetNoLines() != this->GetNoLines())
		{
			return false;
		}

		if (Other.GetNoColumns() != this->GetNoColumns())
		{
			return false;
		}

		for (size_t i = 0; i < this->matrix.size(); ++i)
		{
			for (size_t j = 0; j < this->matrix[i].size(); ++j)
			{
				if (this->matrix[i][j] != Other.matrix[i][j])
				{
					return false;
				}
			}
		}

		return true;
	}

	friend 
	std::ostream &
	operator<<(
		_Inout_ std::ostream &Stream, 
		_In_ const Matrix<T>& Matrix
	) 
	{
		Stream << Matrix.GetNoLines() << " ";
		Stream << Matrix.GetNoColumns() << std::endl;

		for (size_t i = 0; i < Matrix.GetNoLines(); ++i)
		{
			for (size_t j = 0; j < Matrix.GetNoColumns(); ++j)
			{
				Stream << Matrix.matrix[i][j] << " ";
			}
			Stream << std::endl;
		}

		return Stream;
	}

private:
	std::vector<std::vector<T> > matrix;
};

#endif // MATRIX_HPP