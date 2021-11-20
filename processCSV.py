import csv
cnt = 0
with open('resample.csv') as f1:
    reader = csv.reader(f1)
    f1list = list(reader)
    print(len(f1list))
    flag = 0
    with open('temp_accuracy2.csv', 'w') as f2:
        writer = csv.writer(f2)
        for row in f1list:
            if flag == 0:
                flag = 1
            else:
                temp = int(row[1]) * 1.3
                row.append(str(temp))
                row.append(str(int(row[1]) + temp))
                writer.writerow(row)
                cnt += 1
                if cnt % 1000 == 0:
                    print(cnt)
                if cnt == 15:
                    break