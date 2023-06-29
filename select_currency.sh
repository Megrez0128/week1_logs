#!/bin/bash
grep "INFO - Currency|" 111.1 > test.txt
grep -E '^.{25}INFO - Currency' 111.1 > test.txt
# 读取currency_flow.txt文件，将筛选出的行存入test.txt文件
