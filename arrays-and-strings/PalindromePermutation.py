def find_char_freq(string):
    freq = dict()
    for c in string:
        if c in freq:
            freq[c] += 1
        else:
            freq[c] = 1
    return freq


def palindromePermutation(string):
    string = string.replace(" ", "").lower()
    char_freq = find_char_freq(string)
    odd_count = 0
    for c in char_freq.values():
        if c % 2 == 1:
            odd_count += 1
    
    return False if odd_count > 1 else True
