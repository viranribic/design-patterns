.file	"task4.cpp"
	.intel_syntax noprefix
.lcomm _std::__ioinit,1,1
	.section	.text$PlainOldClass::set(int),"x"
	.linkonce discard
	.align 2
	.globl	_PlainOldClass::set(int)
	.def	_PlainOldClass::set(int);	.scl	2;	.type	32;	.endef
_PlainOldClass::set(int):
LFB971:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	edx, DWORD PTR [ebp 8]
	mov	DWORD PTR [eax], edx
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret	4
	.cfi_endproc
LFE971:
	.section	.text$CoolClass::set(int),"x"
	.linkonce discard
	.align 2
	.globl	_CoolClass::set(int)
	.def	_CoolClass::set(int);	.scl	2;	.type	32;	.endef
_CoolClass::set(int):
LFB973:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	edx, DWORD PTR [ebp 8]
	mov	DWORD PTR [eax 4], edx
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret	4
	.cfi_endproc
LFE973:
	.section	.text$CoolClass::get(),"x"
	.linkonce discard
	.align 2
	.globl	_CoolClass::get()
	.def	_CoolClass::get();	.scl	2;	.type	32;	.endef
_CoolClass::get():
LFB974:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	eax, DWORD PTR [eax 4]
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE974:
	.section	.text$Base::Base(),"x"
	.linkonce discard
	.align 2
	.globl	_Base::Base()
	.def	_Base::Base();	.scl	2;	.type	32;	.endef
_Base::Base():
LFB978:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	DWORD PTR [eax], OFFSET FLAT:_vtable for Base 8
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE978:
	.section	.text$CoolClass::CoolClass(),"x"
	.linkonce discard
	.align 2
	.globl	_CoolClass::CoolClass()
	.def	_CoolClass::CoolClass();	.scl	2;	.type	32;	.endef
_CoolClass::CoolClass():
LFB981:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	ecx, eax
	call	_Base::Base()
	mov	eax, DWORD PTR [ebp-4]
	mov	DWORD PTR [eax], OFFSET FLAT:_vtable for CoolClass 8
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE981:
	.def	___main;	.scl	2;	.type	32;	.endef
	.text
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB975:
	.cfi_startproc
	.cfi_personality 0,___gxx_personality_v0
	.cfi_lsda 0,LLSDA975
	lea	ecx, [esp 4]
	.cfi_def_cfa 1, 0
	and	esp, -16
	push	DWORD PTR [ecx-4]
	push	ebp
	.cfi_escape 0x10,0x5,0x2,0x75,0
	mov	ebp, esp
	push	ebx
	push	ecx
	.cfi_escape 0xf,0x3,0x75,0x78,0x6
	.cfi_escape 0x10,0x3,0x2,0x75,0x7c
	sub	esp, 32
	call	___main
	mov	DWORD PTR [esp], 8
LEHB0:
	call	_operator new(unsigned int)
	mov	ebx, eax
	mov	ecx, ebx
	call	_CoolClass::CoolClass()
	mov	DWORD PTR [ebp-12], ebx
	lea	eax, [ebp-16]
	mov	DWORD PTR [esp], 42
	mov	ecx, eax
	call	_PlainOldClass::set(int)
	sub	esp, 4
	mov	eax, DWORD PTR [ebp-12]
	mov	eax, DWORD PTR [eax]
	mov	edx, DWORD PTR [eax]
	mov	eax, DWORD PTR [ebp-12]
	mov	DWORD PTR [esp], 42
	mov	ecx, eax
	call	edx
LEHE0:
	sub	esp, 4
	mov	eax, 0
	jmp	L11
L10:
	mov	DWORD PTR [esp], eax
LEHB1:
	call	__Unwind_Resume
LEHE1:
L11:
	lea	esp, [ebp-8]
	pop	ecx
	.cfi_restore 1
	.cfi_def_cfa 1, 0
	pop	ebx
	.cfi_restore 3
	pop	ebp
	.cfi_restore 5
	lea	esp, [ecx-4]
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE975:
	.def	___gxx_personality_v0;	.scl	2;	.type	32;	.endef
	.section	.gcc_except_table,"w"
LLSDA975:
	.byte	0xff
	.byte	0xff
	.byte	0x1
	.uleb128 LLSDACSE975-LLSDACSB975
LLSDACSB975:
	.uleb128 LEHB0-LFB975
	.uleb128 LEHE0-LEHB0
	.uleb128 L10-LFB975
	.uleb128 0
	.uleb128 LEHB1-LFB975
	.uleb128 LEHE1-LEHB1
	.uleb128 0
	.uleb128 0
LLSDACSE975:
	.text
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
	.text
	.def	___tcf_0;	.scl	3;	.type	32;	.endef
___tcf_0:
LFB983:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 8
	mov	ecx, OFFSET FLAT:_std::__ioinit
	call	_std::ios_base::Init::~Init()
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE983:
	.def	___static_initialization_and_destruction_0(int, int);	.scl	3;	.type	32;	.endef
___static_initialization_and_destruction_0(int, int):
LFB982:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 24
	cmp	DWORD PTR [ebp 8], 1
	jne	L13
	cmp	DWORD PTR [ebp 12], 65535
	jne	L13
	mov	ecx, OFFSET FLAT:_std::__ioinit
	call	_std::ios_base::Init::Init()
	mov	DWORD PTR [esp], OFFSET FLAT:___tcf_0
	call	_atexit
L13:
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE982:
	.def	__GLOBAL__sub_I_main;	.scl	3;	.type	32;	.endef
__GLOBAL__sub_I_main:
LFB984:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 24
	mov	DWORD PTR [esp 4], 65535
	mov	DWORD PTR [esp], 1
	call	___static_initialization_and_destruction_0(int, int)
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE984:
	.section	.ctors,"w"
	.align 4
	.long	__GLOBAL__sub_I_main
	.ident	"GCC: (GNU) 4.8.1"
	.def	_operator new(unsigned int);	.scl	2;	.type	32;	.endef
	.def	__Unwind_Resume;	.scl	2;	.type	32;	.endef
	.def	___cxa_pure_virtual;	.scl	2;	.type	32;	.endef
	.def	_std::ios_base::Init::~Init();	.scl	2;	.type	32;	.endef
	.def	_std::ios_base::Init::Init();	.scl	2;	.type	32;	.endef
	.def	_atexit;	.scl	2;	.type	32;	.endef