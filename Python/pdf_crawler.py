import requests
from bs4 import BeautifulSoup

"""
a simple script to download all pptx from cs61-sp15 offered by UCBerkeley.

crawler() get all link endswith 'pptx'
download() downloads all links returned by crawler()
"""
def crawler():
    head_url = "http://www-inst.eecs.berkeley.edu/~cs61c/sp15/lec/" #url to get
    real_url = []

    for i in range(1, 26):
        if i < 10:
            real_url.append(head_url + '0' + str(i) + '/')
        else:
            real_url.append(head_url + '' + str(i) + '/')
    result = []

    headers = {
        'User-Agent': "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36"
        } #use headers to inmitate a browser's action

    for url in real_url:
        response = requests.request("GET", url, headers=headers)
        soup = BeautifulSoup(response.text, "lxml") #Parse the web file
        for link in soup.find_all('a'):
            try:
                if link.get('href').endswith('pptx'):
                    result.append(url+link.get('href')) #get all a_attribute link to a list
            except:
                pass
    print(len(result))

    return result

def download(link, index):
    filename = str(index) + '-' + link.split('/')[-1]
    r = requests.get(link, stream=True)
    count = 0
    with open(filename, 'wb') as f:
        print('Downloading File {0}'.format(index), end='')
        for chunk in r.iter_content(1024):
            if chunk:
                f.write(chunk)
                count += 1
            else:
                break
            if count == 10:
                print('¡ú',end='') # indicating the downloading is fine.
                count = 0
    print()
def main():
    links = crawler()
    count = 1
    for link in links:
        download(link, count)
        count += 1
    print('Download Done.')

if __name__ == '__main__':
    main()
