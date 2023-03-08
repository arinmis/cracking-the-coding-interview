from collections import defaultdict

def to_freq_dict(string):
    freq = defaultdict(int)
    for char in string:
        freq[char] += 1
    return dict(freq)

''' 
takes O(max(a, b)) where a and b are 
length of the given string
''' 
def check_permutation(str1, str2):
    # length of the strings must be same
    if not len(str1) == len(str2):
        return False
    return to_freq_dict(str1) ==  to_freq_dict(str2)
