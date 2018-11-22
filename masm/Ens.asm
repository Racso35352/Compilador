pila segment para stack 'stack'
db 1000 dup(?)
pila ends
datos segment para public 'data'
a DB '7$'
b DB '16$'
c DB '2$'
x DB '131$'
y DB '4046$'
z DB '30$'
datos ends
extra segment para public 'data'
extra ends
assume	cs:codigo, ds:datos, ss:pila,	es:extra
public p0
codigo segment para public 'code'
p0	proc far 
push ds
mov ax, 0
push ax
mov ax,datos
mov ds,ax
mov ax, extra
mov es,ax
mov ah,06
mov al,0
mov bh,7
mov ch,00
mov cl,0
mov dh,24
mov dl,79
int 10h
	ret
p0	endp


codigo ends
	end p0
