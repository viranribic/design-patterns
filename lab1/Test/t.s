.file	"1lab4.cpp"
	.intel_syntax noprefix
	.section	.text$CoolClass::set(int),"x"
	.linkonce discard
	.align 2
	.globl	_CoolClass::set(int)
	.def	_CoolClass::set(int);	.scl	2;	.type	32;	.endef
_CoolClass::set(int):
	push	ebp
	mov	ebp, esp
	sub	esp, 4 ;prostor za "lokalne varijable"
	mov	DWORD PTR [ebp-4], ecx;referenca samog objekta
	mov	eax, DWORD PTR [ebp-4] ;adresa objekta u eax, žongliranje
	mov	edx, DWORD PTR [ebp 8] ;42 sa stoga
	mov	DWORD PTR [eax 4], edx ;42 na adresu varijable, eax ima referencu na objekt, na eax se nalazi prvi element objekta a to je vtablica, na eax+4 je varijabla x
	leave
	ret	4 ;ret 4 - vraća se na povratnu adresu i još miče 4 bajta sa stoga
	.section	.text$CoolClass::get(),"x"
	.linkonce discard
	.align 2
	.globl	_CoolClass::get()
	.def	_CoolClass::get();	.scl	2;	.type	32;	.endef
_CoolClass::get():
	push	ebp
	mov	ebp, esp
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	eax, DWORD PTR [eax 4]
	leave
	ret
	.section	.text$PlainOldClass::set(int),"x"
	.linkonce discard
	.align 2 ;
	.globl	_PlainOldClass::set(int)
	.def	_PlainOldClass::set(int);	.scl	2;	.type	32;	.endef
_PlainOldClass::set(int):
	push	ebp ;stavljanje starog ebp na stog (base pointer)
	mov	ebp, esp ;novi base pointer je isti stack pointer
	sub	esp, 4 ;u naredbi iza će se na ovo mjesto stavit referenca objekta
	mov	DWORD PTR [ebp-4], ecx ;referenca samog objekta
	mov	eax, DWORD PTR [ebp-4] ;adresa objekta
	mov	edx, DWORD PTR [ebp 8] ;42 sa stoga, ebp-4 ima referencu, ebp ima stari ebp, ebp+4 return adresu, ebp+8 ima parametar sa stoga 
	mov	DWORD PTR [eax], edx ;42 na adresu varijable
	leave
	ret	4
	.section	.text$Base::Base(),"x"
	.linkonce discard
	.align 2
	.globl	_Base::Base()
	.def	_Base::Base();	.scl	2;	.type	32;	.endef
_Base::Base():
	push	ebp
	mov	ebp, esp
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx ;referenca objekta
	mov	eax, DWORD PTR [ebp-4]
	mov	DWORD PTR [eax], OFFSET FLAT:_vtable for Base 8;spajanje vtable za base klasu
	leave
	ret
	.section	.text$CoolClass::CoolClass(),"x"
	.linkonce discard
	.align 2
	.globl	_CoolClass::CoolClass()
	.def	_CoolClass::CoolClass();	.scl	2;	.type	32;	.endef
_CoolClass::CoolClass():
	push	ebp
	mov	ebp, esp
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx ;referenca objekta na [ebp-4]
	mov	eax, DWORD PTR [ebp-4] ;referenca objekta sprema na eax
	mov	ecx, eax ;referenca opet u ecx, iz ecx natrag u ecx u 3 naredbe
	call	_Base::Base();poziv base konstruktora
	mov	eax, DWORD PTR [ebp-4]
	mov	DWORD PTR [eax], OFFSET FLAT:_vtable for CoolClass 8;spajanje vtable coolclass klase
	leave
	ret
	.def	___main;	.scl	2;	.type	32;	.endef
	.text
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
	lea	ecx, [esp 4]
	and	esp, -16
	push	DWORD PTR [ecx-4]
	push	ebp ;ebp,
	mov	ebp, esp
	push	ebx 
	push	ecx 
	sub	esp, 32 
	call	___main
	mov	DWORD PTR [esp], 4 ;mislim da je veličina alociranog prostora
	call	_operator new(unsigned int);kreiranje plainoldclass objekta
	mov	DWORD PTR [ebp-12], eax ;konstruktora nema jer ne treba inicijalizirat varijable, ni povezat sa vtablicom, na [ebp-12] je alocirano 4 bajta
	mov	DWORD PTR [esp], 8 ;mislim da je veličina alociranog prostora
	call	_operator new(unsigned int);kreiranje coolclass objekta
	mov	ebx, eax
	mov	ecx, ebx ;na ecx se stavlja referenca na objekt prije pozivanja funkcije njegovog razreda
	call	_CoolClass::CoolClass();pozivanje coolclass konstruktora
	mov	DWORD PTR [ebp-16], ebx  ;na [ebp-16] se nalazi coolclass objekt
	mov	eax, DWORD PTR [ebp-12] ;dohvaćanje plainoldclass objekta 
	mov	DWORD PTR [esp], 42 ;stavljanje 42 na stog kao parametar za ::set()
	mov	ecx, eax ;u ecx je referenca na objekt, parametri idu preko stoga, referenca objekta preko registra
	call	_PlainOldClass::set(int) ;nepolimorfni poziv set()
	sub	esp, 4
	mov	eax, DWORD PTR [ebp-16] ;adresa objekta
	mov	eax, DWORD PTR [eax] ;adresa vtable
	mov	edx, DWORD PTR [eax] ;adresa funkcije (prvo mjesto u tablici- nema pomaka)
	mov	eax, DWORD PTR [ebp-16] ;adresa objekta
	mov	DWORD PTR [esp], 42 ;parametar
	mov	ecx, eax ;u ecx adresa objekta
	call	edx ;polimorfni poziv funkcije
	sub	esp, 4
	mov	eax, 0
	lea	esp, [ebp-8]
	pop	ecx
	pop	ebx
	pop	ebp
	lea	esp, [ecx-4]
	ret
	.globl	_vtable for CoolClass
	.section	.rdata$vtable for CoolClass,"dr"
	.linkonce same_size
	.align 8
_vtable for CoolClass:
	.long	0
	.long	_typeinfo for CoolClass
	.long	_CoolClass::set(int)
	.long	_CoolClass::get()
	.globl	_vtable for Base
	.section	.rdata$vtable for Base,"dr"
	.linkonce same_size
	.align 8
_vtable for Base:
	.long	0
	.long	_typeinfo for Base
	.long	___cxa_pure_virtual
	.long	___cxa_pure_virtual
	.globl	_typeinfo name for CoolClass
	.section	.rdata$typeinfo name for CoolClass,"dr"
	.linkonce same_size
_typeinfo name for CoolClass:
	.ascii "9CoolClass\0"
	.globl	_typeinfo for CoolClass
	.section	.rdata$typeinfo for CoolClass,"dr"
	.linkonce same_size
	.align 4
_typeinfo for CoolClass:
	.long	_vtable for __cxxabiv1::__si_class_type_info 8
	.long	_typeinfo name for CoolClass
	.long	_typeinfo for Base
	.globl	_typeinfo name for Base
	.section	.rdata$typeinfo name for Base,"dr"
	.linkonce same_size
_typeinfo name for Base:
	.ascii "4Base\0"
	.globl	_typeinfo for Base
	.section	.rdata$typeinfo for Base,"dr"
	.linkonce same_size
	.align 4
_typeinfo for Base:
	.long	_vtable for __cxxabiv1::__class_type_info 8
	.long	_typeinfo name for Base
	.ident	"GCC: (tdm64-2) 4.8.1"
	.def	_operator new(unsigned int);	.scl	2;	.type	32;	.endef
	.def	___cxa_pure_virtual;	.scl	2;	.type	32;	.endef