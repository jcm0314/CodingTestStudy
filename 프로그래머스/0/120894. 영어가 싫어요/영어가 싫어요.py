def solution(numbers):
    word_to_number = {'zero': '0', 'one': '1', 'two': '2', 'three': '3', 'four': '4', 'five': '5', 'six': '6', 'seven': '7', 'eight': '8', 'nine': '9'}

    for word, number in word_to_number.items():
        numbers = numbers.replace(word, number)

    return int(numbers)

# 테스트
input_string = "onetwothree"
result = solution(input_string)
print(result)
# 출력: 123
