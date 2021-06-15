### 替换换行new的情况
`LambdaQueryWrapper (\w+ = new LambdaQueryWrapper)(.*\n +\w+\.\w+\()(\w+):`

`LambdaQueryWrapper<$3> $1<$3>$2$3:`

### 替换单行lambdaquery
`LambdaQueryWrapper (\w+ = )(\(LambdaQueryWrapper\) \(?)+new LambdaQueryWrapper\(\)(\.\w+\()(\w+)`

`LambdaQueryWrapper<$4> $1new LambdaQueryWrapper<$4>()$3$4`

### 替换单个
`\(Wrapper\)new LambdaQueryWrapper\(\)\.eq\((\w+)`
`new LambdaQueryWrapper<$1>\(\)\.eq\($1`

