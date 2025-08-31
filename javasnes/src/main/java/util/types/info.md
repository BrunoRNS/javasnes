# Working with types in javasnes

## Processor and Process

The processor is the process that executes other processes while waiting for VBlank, in NTSC systems it executes at most in 60 Hz and in PAL systems it executes at most in 50 Hz.

The process can be called by processor or by other processes. The process is the basic unit of execution in javasnes.

It is recommended to use signals to synchronize processes, signals can be used as global variables, which makes the programming more easier then calcullating the same value every time or calcullating the Vblank count to know when to execute a process.

Thats I call Signed-Oriented-Programming (SOP), and for it, I created a design pattern called FORMS.

## TODO: continue documentation
