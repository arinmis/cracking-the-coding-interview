from random import randint

def gen_matrix(n):
    m = []
    for _ in range(n):
        row = []
        for _ in range(n):
            row.append(randint(1, 100))
        m.append(row)
    return m


def rotated_indexes(indexes, n):
    return (indexes[1], n - indexes[0] - 1)

def rotate_four(index, m):
    indexes = [index]
    for _ in range(3):
        index = rotated_indexes(index, len(m))
        indexes.append(index)
    temp = m[indexes[0][0]][indexes[0][1]]
    m[indexes[0][0]][indexes[0][1]] =  m[indexes[3][0]][indexes[3][1]]
    m[indexes[3][0]][indexes[3][1]] = m[indexes[2][0]][indexes[2][1]]
    m[indexes[2][0]][indexes[2][1]] = m[indexes[1][0]][indexes[1][1]]
    m[indexes[1][0]][indexes[1][1]] = temp

def rotate_90(m):
    for i in range(len(m) - 1):
        for j in range(i, len(m) - i - 1):
            rotate_four((i, j), m)



def print_matrix(m):
    for row in m:
        print(" ".join(map(lambda x: str(x), row)))

"""
# Driver code
m = [[1, 2, 3, 4],
     [5, 6, 7, 8],
     [9, 10, 11, 12],
     [13, 14, 15, 16]]

m = [[1, 2, 3],
     [4, 5, 6],
     [7, 8, 9]]

# m = generate_matrix(5)
print_matrix(m)
print("---")
rotate_90(m)
print_matrix(m)
"""

