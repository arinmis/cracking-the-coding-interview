def char_freq(string):
    freq = dict()
    for c in string:
        if c in freq:
            freq[c] += 1
        else:
            freq[c] = 1
    return freq

def find_freq_diff(freq1, freq2):
    diff_count = 0
    for k in freq1:
        if k in freq2:
            diff_count += abs(freq2[k] - freq2[k])
        else:
            diff_count += freq1[k]
    return diff_count

def one_away(str1, str2):
    if abs(len(str1) - len(str2)) > 1:
        return False
    freq1 = char_freq(str1)
    freq2 = char_freq(str2)
    print(freq1, freq2)
    if find_freq_diff(freq1, freq2) > 1:
        return False
    return True

diff = one_away("pale", "bake")
print(diff)
