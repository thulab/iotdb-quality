from marktex import api
import os

def clean(filename):
    fout = open('temp.tex','w',encoding='utf-8')
    with open(filename,'r',encoding="utf-8") as fin:
        ss=fin.readlines()
        for i in range(len(ss)):
            s=ss[i]
            if i>=8 and i<len(ss)-1: # 略去头尾的格式语句
                if s.endswith('%\n'): # 删去行末的百分号
                    fout.write(s[0:-2]) 
                    fout.write('\n')
                else:
                    fout.write(s)
    fout.close()
    os.remove(filename)
    os.rename('temp.tex',filename)


def show_files(path, all_files):
    # 首先遍历当前目录所有文件及文件夹
    file_list = os.listdir(path)
    # 准备循环判断每个元素是否是文件夹还是文件，是文件的话，把名称传入list，是文件夹的话，递归
    for file in file_list:
        # 利用os.path.join()方法取得路径全名，并存入cur_path变量，否则每次只能遍历一层目录
        cur_path = os.path.join(path, file)
        # 判断是否是文件夹
        if os.path.isdir(cur_path):
            show_files(cur_path, all_files)
        else:
            all_files.append(cur_path)

    return all_files


if __name__ == "__main__": 


    ## 中文文档
    fs = show_files('../../docs/zh',[])
    for f in fs:
        # print(f)
        if f.endswith('Download.md'):
            # 由于该文件使用了无法转换的html语法，跳过该文件
            continue
        api.convert(f,output_dir='output_zh')
        tmp = f.split('\\')
        tmp = 'output_zh/'+ tmp[-1].replace('.md','.tex')
        clean(tmp)

    ## 英文文档
    api.convert('../../README.md',output_dir='output_en')
    clean('output_en/README.tex')
    fs = show_files('../../docs/en',[])
    for f in fs:
        # print(f)
        if f.endswith('Download.md'):
            # 由于该文件使用了无法转换的html语法，跳过该文件
            continue
        api.convert(f,output_dir='output_en')
        tmp = f.split('\\')
        tmp = 'output_en/'+ tmp[-1].replace('.md','.tex')
        clean(tmp)



