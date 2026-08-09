# test_semantic_errors.py

# خطأ 1: REDEFINE_FUNC
def calculate(x, y):
    return x + y

def calculate(a, b):
    return a - b

# خطأ 2: WRONG_ARG_COUNT
result = calculate(1, 2, 3)

# خطأ 3: MISSING_RETURN
def noReturn(x):
    z = x + 1

# خطأ 4: UNUSED_VAR
def unusedVar():
    used = 1
    unused = 2
    return used

# خطأ 5: DEAD_CODE
def deadCode():
    return 1
    x = 2

# خطأ 6: UNDEFINED_VAR
def useUndefined():
    return ghost + 1

# خطأ 7: UNDEFINED_FUNC
output = unknownFunc(5)

# خطأ 8: REDEFINE_VAR
def redefineVar():
    x = 1
    x = 2
    return x