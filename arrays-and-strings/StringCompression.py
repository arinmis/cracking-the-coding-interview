def compress_string(string):
    string += "$" # to count last seequence
    i = 1
    current_char = string[i]
    count = 1
    result = [] 
    while i < len(string):
        if string[i] == current_char:
            count += 1
        else:
            result.append(current_char)
            result.append(str(count))
            current_char = string[i]
            count = 1
        i += 1
    return "".join(result)

compressed = compress_string("aabcccccaaa")
print(compressed)
