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
