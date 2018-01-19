#ifndef  PROGCTL_HPP
#define PROGCTL_HPP

#include <stdlib.h>
#include <unistd.h>
#include <string>

class ProgCtl {
    private:
      std::string   m_program;
      std::string   m_fullprogram;
      pid_t         m_pid;

      void          find_pid();

    public:
      // Constructors
      ProgCtl():m_program(""),m_fullprogram(""),m_pid(-1){}
      ProgCtl(std::string prog):m_program(prog),m_fullprogram(""),m_pid(-1){}
      ProgCtl(char *prog):m_fullprogram(""),m_pid(-1) {
          m_program = std::string(prog);
      }
      // Setters
      void       setName(std::string prog);
      void       setName(char *prog);
      void       setPid(pid_t pid){m_pid = pid;}
      // Other
      bool       alive();
      int        send(int sig);
      int        start();
};

#endif
