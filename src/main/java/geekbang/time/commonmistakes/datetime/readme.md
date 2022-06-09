## 用好Java 8的日期时间类，少踩一些"老三样"的坑
- 初始化日期时间： [newdate](newdate/)
- "恼人"的时区问题：[timezone](timezone/)
- 日期时间格式化和解析：[dateformat](dateformat/)
- 日期时间的计算：[calc](calc/)
### 备注
#### 标准库API
- java.util(旧)  
    - `Date`, `Calendar`, `TimeZone`
    - 格式化：`SimpleDateFormat`
- java.time(Java 8引入)  
    - 本地日期和时间：`LocalDateTime`,`LocalDate`,`LocalTime` 
    - 带时区的日期和时间：`ZonedDateTime`
    - 时刻：`Instant`
    - 时区：`ZoneId`,`ZoneOffset`
    - 时间间隔：`Duration`
    - 新的格式化：`DateTimeFormatter`