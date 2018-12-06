pila segment para stack 'stack'
db 1000 dup(?)
pila ends
datos segment para public 'data'
no_val DB 'Dato invalido, se espera un numero. Terminando programa$'
a DW 0
b DW 16
x DW 0
y DW 0
z DW 0
k DB 61,?,61 DUP(?)
hu DB 61,?,61 DUP(?)
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
mov dl,25
int 10h
lea dx,k
mov ah,0ah
int 21h
lea bx,k
inc bx
mov cx,[bx]
mov ch,0
ciclo0:
inc bx
mov dl,[bx]
cmp dl,48
jb sale0
cmp dl,57
ja sale0
loop ciclo0
mov ax,byte ptr 0
jmp fin0
sale0:
mov ax,1
fin0:
cmp ax,byte ptr 1
jne val0
lea dx,no_val
mov ah,9
int 21h
mov ah,4ch
int 21h
val0:
mov ah,2
mov bh,0
mov dh,2
mov dl,25
int 10h
lea dx,hu
mov ah,0ah
int 21h
mov ah,2
mov bh,0
mov dh,3
mov dl,25
int 10h
lea bx,k
inc bx
mov cx,[bx]
mov ch,0
Lk1:
inc bx
mov dl,[bx]
mov ah,2
int 21h
loop Lk1
mov ah,2
mov bh,0
mov dh,4
mov dl,25
int 10h
lea bx,hu
inc bx
mov cx,[bx]
mov ch,0
Lhu2:
inc bx
mov dl,[bx]
mov ah,2
int 21h
loop Lhu2
	ret
p0	endp


codigo ends
	end p0
