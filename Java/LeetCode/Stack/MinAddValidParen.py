# LC 921 Stack
class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        stack = []
        for c in s:
            if len(stack) == 0:
                stack.append(c)
                continue
            top = stack[-1]
            if (top == '(' and c == ')'):
                stack.pop()
            else:
                stack.append(c)
        return len(stack)
    
sol = Solution()
s = "())"
print(sol.minAddToMakeValid(s))
s = "((("
print(sol.minAddToMakeValid(s))
s = "()"
print(sol.minAddToMakeValid(s))
s = "()))(("
print(sol.minAddToMakeValid(s))
