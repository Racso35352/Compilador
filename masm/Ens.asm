pila segment para stack 'stack'
db 1000 dup(?)
pila ends
datos segment para public 'data'
b db "8$"
n db "0$"
k db "buenos$"
m db "malos$"
p db "17$"
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
mov ah,2
mov bh,0
mov dh,1
mov dl,35
int 10h

LEA dx,b
mov ah,09
int 21H
mov ah,2
mov bh,0
mov dh,2
mov dl,35
int 10h

LEA dx,n
mov ah,09
int 21H
mov ah,2
mov bh,0
mov dh,3
mov dl,35
int 10h

LEA dx,k
mov ah,09
int 21H
mov ah,2
mov bh,0
mov dh,4
mov dl,35
int 10h

LEA dx,m
mov ah,09
int 21H
mov ah,2
mov bh,0
mov dh,5
mov dl,35
int 10h

LEA dx,p
mov ah,09
int 21H
	ret
p0	endp


codigo ends
	end p0
