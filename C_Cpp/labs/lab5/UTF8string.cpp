#include <iostream>
#include <string>
#include <cstring>

#include "UTF8string.hpp"
#include "utf8.h"


size_t UTF8string::length() {
  size_t utf8_len;
  unsigned char *cstr = (unsigned char *)m_s.c_str();

  utf8_len = (size_t)utf8_charlen(cstr);

  return utf8_len;
}

size_t UTF8string::bytes() {
  size_t bytes = 0;
  bytes = (size_t)utf8_charpos_to_bytes((unsigned char *)m_s.c_str(), length());
  return bytes;
}


int UTF8string::find(std::string substr) {
  unsigned char *sub_cstr = (unsigned char*)substr.c_str();
  unsigned char *cstr = (unsigned char*)m_s.c_str();
  unsigned char *pos = utf8_search(cstr, sub_cstr);

  if (pos) {
    int chars_cstr, chars_substr;

    chars_cstr = utf8_bytes_to_charpos(cstr, (int)std::strlen((char *)cstr));
    chars_substr = utf8_bytes_to_charpos(pos, (int)std::strlen((char *)pos));

    return (chars_cstr - chars_substr + 1);
  } else {
    return 0;
  }



}

void UTF8string::replace(UTF8string to_remove, UTF8string replacement) {

  //DEBUG: consider replacement including to_remove;***********

  int remove_index = find(to_remove.value());
  int old_index = 0;
  size_t bytes_in_remove = to_remove.bytes();
  size_t bytes_before_remove;


  while ((size_t)remove_index < length() && old_index != remove_index) {
    old_index = remove_index;
    bytes_before_remove = (size_t)utf8_charpos_to_bytes((unsigned char *)m_s.c_str(), remove_index - 1);
    m_s.replace(bytes_before_remove, bytes_in_remove, (char *)replacement.value().c_str());

    if (remove_index) {
      unsigned char *sub_cstr = (unsigned char*)to_remove.value().c_str();
      unsigned char *cstr = (unsigned char*)m_s.c_str();
      for (int i = 0; i < remove_index + (int)replacement.length() - 1; i++) {
        _utf8_incr(cstr);
      }

      //std::cout << "modified string:" << cstr <<'\n';
      //std::cout << "string to search: " << sub_cstr << '\n';
      unsigned char *pos = utf8_search(cstr, sub_cstr);

      if (pos) {
        remove_index = remove_index + utf8_bytes_to_charpos(cstr, (int)std::strlen((char *)cstr))
         - utf8_bytes_to_charpos(pos, (int)std::strlen((char *)pos)) + (int)replacement.length();
      }

    }

  }

  return;

}


void UTF8string::operator+=(UTF8string str1) {
  m_s = m_s + str1.m_s;
}

std::ostream &operator<<(std::ostream &os, const UTF8string &utf8String) {
    os << utf8String.m_s;
    return os;
}

UTF8string operator+(UTF8string str1, UTF8string str2) {
  UTF8string result(str1.m_s + str2.m_s);
  return result;
}


UTF8string operator*(int times, UTF8string utf8String) {
  std::string result = "";
  std::string str = utf8String.m_s;
  for (int i = 0; i < times; i++) {
    result += str;
  }
  return UTF8string(result);
}

UTF8string operator*(UTF8string utf8String, int times) {
  std::string result = "";
  std::string str = utf8String.m_s;
  for (int i = 0; i < times; i++) {
    result += str;
  }
  return UTF8string(result);
}

UTF8string operator!(UTF8string utf8String) {
  //try to use demo.c to obtain the byte num of one character in a string and construct another char* to form a reverse one.
  unsigned char *cstr = (unsigned char*)utf8String.m_s.c_str();
  unsigned char *p;
  p = cstr;
  unsigned int codepoint;
  int bytes_in_char;

  int i = utf8String.bytes();
  int j = 0;
  unsigned char reverse_str[i + 1];


  reverse_str[i] = '\0';
  //i--;

  while (*p) {
    codepoint = utf8_to_codepoint(p, &bytes_in_char);

    if (codepoint) {
      for (int k = 0; k < bytes_in_char; k++) {
        reverse_str[i - bytes_in_char + k] = cstr[j + k];
      }

      i -= bytes_in_char;
      j += bytes_in_char;
      _utf8_incr(p);
    } else {
      p++;

    }
  }


  /***
  if (i == 0 && j==(int)utf8String.bytes()) {
    std::cout << "SUCCESS" << '\n';
  }***/

  return UTF8string((char *)reverse_str);

}
