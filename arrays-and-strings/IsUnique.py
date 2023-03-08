'''
check char uniqueness with set
complexity: O(n)
'''
def is_unique(string):
    return len(string) == len(set(string))

'''
check uniqueness with nested loop
complexity: O(n^2)
'''
def is_unique_with_loop(string):
    print(string[i],  string[i + 1:])
    for i in range(len(string) - 1):
        if string[i] in string[i + 1:]:
        return False
    return True

