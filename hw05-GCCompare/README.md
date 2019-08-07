# Запускаем ru.otus.hw05.GCCompare.main для каждого GC (см. ниже Настройки VM);
# каждый запуск с новым GC формирует лог-файл с данными о запусках GC: тип, продолжительность, причина, время с начала старта порграммы;
# название лог-файла соответствует опции GC в настройках VM;
# лог-файлы лежат в папке .\hw05-GCCompare\data;
# лог-файлы необходимы для постороения итоговой таблцы, которая строится запуском ru.otus.hw05.DataCollector.main()

#Настройки VM (только для ru.otus.hw05.GCCompare):
#  Общие:
#    -Xms512m
#    -Xmx512m
#    -Xlog:gc=debug:file=.\hw05-GCCompare\logs\gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
#    -XX:+HeapDumpOnOutOfMemoryError
#    -XX:HeapDumpPath=.\hw05-GCCompare\logs\dump

#    Настройки GC:
#       1) -XX:+UseG1GC
#       2) -XX:+UseConcMarkSweepGC
#       3) -XX:+UseSerialGC                            
#       4) -XX:+UseParallelGC
#       5) -XX:+UnlockExperimentalVMOptions -XX:+UseZGC (моя VM НЕ поддерживает опцию -XX:+UseZGC)








