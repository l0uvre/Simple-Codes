import requests
from bs4 import BeautifulSoup
import threading

"""
a simple script to download all pdf from one page.

crawler() get all link endswith 'pptx' and 'pdf'
download() downloads all links returned by crawler()
"""
def crawler():
    head_url = "https://www.cs.cmu.edu/~ninamf/courses/601sp15/" #url to get

    result = []

    headers = {
        'User-Agent': "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36"
        } #use headers to inmitate a browser's action

    response = requests.request("GET", head_url + "lectures.shtml", headers=headers)
    soup = BeautifulSoup(response.text, "lxml") #Parse the web file
    for link in soup.find_all('a'):
        try:
            if link.get('href').endswith('pptx') or link.get('href').endswith('pdf'):
                if link.get('href')[0:4] == 'http':
                    result.append(link.get('href'))
                else:
                    result.append(head_url+link.get('href')) #get all a_attribute link to a list
        except:
            pass

    print(len(result)) #
    return result

def download(link, index):
    #filename = str(index) + '-' + link.split('/')[-1]
    filename = link.split('/')[-1]
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
                print('→',end='') # indicating the downloading is fine.
                count = 0
    print()

def main():
    links = crawler()
    threads = []
    count = 1

    for link in links:
        a_thread = threading.Thread(target=download, args=(link, count))
        count += 1
        threads.append(a_thread)

    for a_thread in threads:
        a_thread.start()

    for a_thread in threads:
        a_thread.join()
        
    print('Download Done.')

if __name__ == '__main__':
    main()
