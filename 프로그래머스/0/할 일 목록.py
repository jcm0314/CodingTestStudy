def solution(todo_list, finished):
    return [x for x, b in zip(todo_list, finished) if not b]