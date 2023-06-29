# 日志分析程序

这是一个用于分析游戏日志的Java程序。它可以读取一个 `TLOG`日志文件，对其中的日志进行分析，并输出分析结果。

## 用法

该程序可以通过命令行参数进行配置。以下是可用的命令行参数：

- `-l` 或 `--logFilePath`：指定日志文件的路径。该参数是必需的。
- `-t` 或 `--iZoneAreaID`：指定（临时）游戏服务器的编号。
- `-u` 或 `--vUserID`：指定用户ID。
- `-r` 或 `--vRoleID`：指定玩家角色ID。
- `-m` 或 `--mainReason`：指定日志的主要原因。

程序支持两种不同的功能：

1. 分析日志,并将区服->用户->角色的总消耗额度和总增加额度信息输出到控制台和文件中。

* 要运行第一种功能，请使用以下命令：

`java Main -l <logFilePath>`

其中，`<logFilePath>` 是日志文件的路径。如果没有指定 -l 参数，则程序将默认读取 data 文件夹下的所有日志文件。

2. 按照指定的条件对日志进行过滤，并将筛选出的区服->用户->角色->LogReason的总消耗额度和总增加额度信息输出到控制台中。

* 要运行第二种功能，请使用以下命令：

`java Main -l <logFilePath> -i <iZoneAreaID> -u <vUserID> -r <vRoleID> -m <mainReason>`

其中，`<logFilePath>` 是日志文件的路径，`<iZoneAreaID>`、`<vUserID>`、`<vRoleID>` 和 `<mainReason>` 是过滤条件。如果没有指定 -m 参数，则程序将运行第一种功能。

如果没有指定 -l 参数，则程序将默认读取 data 文件夹下的所有日志文件。

## 输出格式

程序的输出格式如下：


### 第一种功能

程序将分析结果输出到控制台和文件中。输出格式如下：

```
注册的游戏服务器编号 iZoneAreaID:<iZoneAreaID>
	用户ID vUserID:<vUserID>
		玩家角色ID vRoleID:<vRoleID>
		总增加额度: <count1>
		总消耗额度: <count2>
		...
```

其中，`<iZoneAreaID>`、`<vUserID>` 和 `<vRoleID>` 分别是游戏服务器编号、用户ID和玩家角色ID。`<reason1>`、`<mainReason>` 等是额度改变的原因，`<count1>`、`<count2>` 等是总增加额度和总消耗额度。

### 第二种功能

程序将过滤结果输出到控制台和文件中。输出格式如下：

```
注册的游戏服务器编号 iZoneAreaID:<iZoneAreaID>
	用户ID vUserID:<vUserID>
		玩家角色ID vRoleID:<vRoleID>
				主要原因 MainReason:<mainReason>
					总增加额度: <count1>
					总消耗额度: <count2>
                    ...
```

其中，`<iZoneAreaID>`、`<vUserID>`、`<vRoleID>` 和 `<reason1>`、`<reason2>` 等的含义与第一种功能相同。

## 依赖项

该程序使用了以下依赖项：

- `Apache Commons CLI`：用于解析命令行参数。

# 背景信息

## 学习内容：

1. 学习Java语法基础，面向对象，字符串和数组，集合框架，异常处理，文件IO等；
2. 了解 `JVM`内存管理和垃圾回收机制，着重学习 `G1`垃圾回收器；
3. 了解并能使用Bash shell基础命令(find、netstat、iostat、ps、top、文件操作、管道等)，能用grep, awk, sed做基本的文本处理，能用ssh/rsync等命令进行远程资源管理，掌握用man命令来查找资料，了解vim的使用，能用vim编辑文件，在linux环境中能用svn进行版本管理；

## 第一周作业：

根据1、2、5的学习内容，需要做一个简单的分析游戏的BI日志作业，作业要求如下：

1. 准备一份TLOG日志，在日志文件里面需要包含各种不同表结构的日志，需要使用Linux命令把CurrencyFlow表的日志筛选并保存成一个输入文件，然后使用日志分析程序分析CurrencyFlow日志。需要统计出区服->用户->角色->的总消耗额度和总增加额度信息，并把这些信息用TLOG的形式写入到输出文件中。
2. CurrencyFlow日志中还有LogReason，如果需要统计出区服->用户->角色->LogReason的总消耗额度和总增加额度信息，并使用这些数据提供给外部查询，需要怎么设计这个查询服务呢？

## 大致思路

大致思路如下：

1. 定义一个 Log 类，用于表示日志信息，包括区服，用户,角色的总消耗额度和总增加额度，时间、玩家 ID、游戏操作等信息。
2. 定义一个 LogParser 类，用于解析日志文件，将日志文件中的每一行转换为 Log 对象，并将 Log 对象存储到一个 List 中。
3. 定义一个 LogAnalyzer 类，用于分析日志信息，例如统计增加和消耗的额度信息等。
4. 在主函数中，依次调用 LogParser和 LogAnalyzer 类的方法，完成日志文件的解析分析。

## 预备知识整理

### Bash shell基础命令

`grep`是一个在Linux系统中常用的命令行工具，用于在文件中查找指定的字符串。`grep`命令的基本语法如下：

`grep [options] pattern [file ...]`

其中，`pattern`表示要查找的字符串，`file`表示要查找的文件。如果不指定 `file`参数，则 `grep`命令会从标准输入中读取数据。`grep`命令会将包含 `pattern`的行输出到标准输出中。

`grep`命令常用的选项包括：

- `-i`：忽略大小写。
- `-v`：反向查找，输出不包含 `pattern`的行。
- `-w`：匹配整个单词，而不是匹配字符串的一部分。
- `-n`：输出行号。
- `-r`：递归查找子目录中的文件。
- `-c`：只输出匹配的行数，不输出匹配的行。

### 文件IO

### Reader（字符输入流）

`Reader`用于从源头（通常是文件）读取数据（字符信息）到内存中，`java.io.Reader`抽象类是所有字符输入流的父类。

`Reader` 用于读取文本， `InputStream` 用于读取原始字节。

`Reader` 常用方法：

- `read()` : 从输入流读取一个字符。
- `read(char[] cbuf)` : 从输入流中读取一些字符，并将它们存储到字符数组 `cbuf`中，等价于 `read(cbuf, 0, cbuf.length)` 。
- `read(char[] cbuf, int off, int len)`：在 `read(char[] cbuf)` 方法的基础上增加了 `off` 参数（偏移量）和 `len` 参数（要读取的最大字符数）。
- `skip(long n)`：忽略输入流中的 n 个字符 ,返回实际忽略的字符数。
- `close()` : 关闭输入流并释放相关的系统资源。

`InputStreamReader` 是字节流转换为字符流的桥梁，其子类 `FileReader` 是基于该基础上的封装，可以直接操作字符文件。

### Writer（字符输出流）

`Writer`用于将数据（字符信息）写入到目的地（通常是文件），`java.io.Writer`抽象类是所有字符输出流的父类。

`Writer` 常用方法：

- `write(int c)` : 写入单个字符。
- `write(char[] cbuf)`：写入字符数组 `cbuf`，等价于 `write(cbuf, 0, cbuf.length)`。
- `write(char[] cbuf, int off, int len)`：在 `write(char[] cbuf)` 方法的基础上增加了 `off` 参数（偏移量）和 `len` 参数（要读取的最大字符数）。
- `write(String str)`：写入字符串，等价于 `write(str, 0, str.length())` 。
- `write(String str, int off, int len)`：在 `write(String str)` 方法的基础上增加了 `off` 参数（偏移量）和 `len` 参数（要读取的最大字符数）。
- `append(CharSequence csq)`：将指定的字符序列附加到指定的 `Writer` 对象并返回该 `Writer` 对象。
- `append(char c)`：将指定的字符附加到指定的 `Writer` 对象并返回该 `Writer` 对象。
- `flush()`：刷新此输出流并强制写出所有缓冲的输出字符。
- `close()`:关闭输出流释放相关的系统资源。

`OutputStreamWriter` 是字符流转换为字节流的桥梁，其子类 `FileWriter` 是基于该基础上的封装，可以直接将字符写入到文件
