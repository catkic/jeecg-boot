import os
import re
# with open(
#         "E:\\Project\\Hwagain\\java\\jeecg-boot\\jeecg-boot\\jeecg-boot-module-system\\src\\main\\java\\org\\jeecg\\modules\\online\\cgform\\util\\b.java") as f:
#     java = f.read()


def findAllFile(base):
    for root, ds, fs in os.walk(base):
        for f in fs:
            fullname = os.path.join(root, f)
            yield fullname


for file in findAllFile(
        "/home/catkic/Documents/projects/java/jeecg-boot/jeecg-boot/jeecg-boot-module-system/src/main/java/org/jeecg/modules/online/cgreport"):
    with open(file, 'r+', encoding='utf-8') as f:

        java = f.read()
        if '\\u' in java:
            print(file)

            f.seek(0)
            f.write(java.encode("utf-8").decode("unicode_escape"))

# java.encode("utf-8").decode("unicode_escape")
# print('\\u' in "中文")
