#!/bin/bash
grep -E "^Currency|" currency_flow.txt > test.txt
# 读取currency_flow.txt文件，将筛选出的行存入test.txt文件
