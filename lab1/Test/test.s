	.file	"test.cpp"
	.intel_syntax noprefix
.lcomm __ZStL8__ioinit,1,1
	.section	.text$_ZN13PlainOldClass4setXEi,"x"
	.linkonce discard
	.align 2
	.globl	__ZN13PlainOldClass4setXEi
	.def	__ZN13PlainOldClass4setXEi;	.scl	2;	.type	32;	.endef
__ZN13PlainOldClass4setXEi:
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
	mov	edx, DWORD PTR [ebp+8]
	mov	DWORD PTR [eax], edx
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret	4
	.cfi_endproc
LFE971:
	.section	.text$_ZN13PlainOldClass4setDEd,"x"
	.linkonce discard
	.align 2
	.globl	__ZN13PlainOldClass4setDEd
	.def	__ZN13PlainOldClass4setDEd;	.scl	2;	.type	32;	.endef
__ZN13PlainOldClass4setDEd:
LFB973:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 16
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp+8]
	mov	DWORD PTR [ebp-16], eax
	mov	eax, DWORD PTR [ebp+12]
	mov	DWORD PTR [ebp-12], eax
	mov	eax, DWORD PTR [ebp-4]
	fld	QWORD PTR [ebp-16]
	fstp	QWORD PTR [eax+8]
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret	8
	.cfi_endproc
LFE973:
	.section	.text$_ZN13PlainOldClass4setYEi,"x"
	.linkonce discard
	.align 2
	.globl	__ZN13PlainOldClass4setYEi
	.def	__ZN13PlainOldClass4setYEi;	.scl	2;	.type	32;	.endef
__ZN13PlainOldClass4setYEi:
LFB975:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	edx, DWORD PTR [ebp+8]
	mov	DWORD PTR [eax+16], edx
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret	4
	.cfi_endproc
LFE975:
	.section	.text$_ZN9CoolClass3setEi,"x"
	.linkonce discard
	.align 2
	.globl	__ZN9CoolClass3setEi
	.def	__ZN9CoolClass3setEi;	.scl	2;	.type	32;	.endef
__ZN9CoolClass3setEi:
LFB977:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	edx, DWORD PTR [ebp+8]
	mov	DWORD PTR [eax+4], edx
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret	4
	.cfi_endproc
LFE977:
	.section	.text$_ZN9CoolClass3getEv,"x"
	.linkonce discard
	.align 2
	.globl	__ZN9CoolClass3getEv
	.def	__ZN9CoolClass3getEv;	.scl	2;	.type	32;	.endef
__ZN9CoolClass3getEv:
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
	mov	eax, DWORD PTR [eax+4]
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE978:
	.section	.text$_ZN4BaseC2Ev,"x"
	.linkonce discard
	.align 2
	.globl	__ZN4BaseC2Ev
	.def	__ZN4BaseC2Ev;	.scl	2;	.type	32;	.endef
__ZN4BaseC2Ev:
LFB982:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 4
	mov	DWORD PTR [ebp-4], ecx
	mov	eax, DWORD PTR [ebp-4]
	mov	DWORD PTR [eax], OFFSET FLAT:__ZTV4Base+8
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE982:
	.section	.text$_ZN9CoolClassC1Ev,"x"
	.linkonce discard
	.align 2
	.globl	__ZN9CoolClassC1Ev
	.def	__ZN9CoolClassC1Ev;	.scl	2;	.type	32;	.endef
__ZN9CoolClassC1Ev:
LFB985:
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
	call	__ZN4BaseC2Ev
	mov	eax, DWORD PTR [ebp-4]
	mov	DWORD PTR [eax], OFFSET FLAT:__ZTV9CoolClass+8
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE985:
	.def	___main;	.scl	2;	.type	32;	.endef
	.text
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB979:
	.cfi_startproc
	.cfi_personality 0,___gxx_personality_v0
	.cfi_lsda 0,LLSDA979
	lea	ecx, [esp+4]
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
	sub	esp, 48
	call	___main
	mov	DWORD PTR [esp], 8
LEHB0:
	call	__Znwj
	mov	ebx, eax
	mov	ecx, ebx
	call	__ZN9CoolClassC1Ev
	mov	DWORD PTR [ebp-12], ebx
	lea	eax, [ebp-40]
	mov	DWORD PTR [esp], 42
	mov	ecx, eax
	call	__ZN13PlainOldClass4setXEi
	sub	esp, 4
	lea	eax, [ebp-40]
	fld	QWORD PTR LC1
	fstp	QWORD PTR [esp]
	mov	ecx, eax
	call	__ZN13PlainOldClass4setDEd
	sub	esp, 8
	lea	eax, [ebp-40]
	mov	DWORD PTR [esp], 44
	mov	ecx, eax
	call	__ZN13PlainOldClass4setYEi
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
	jmp	L13
L12:
	mov	DWORD PTR [esp], eax
LEHB1:
	call	__Unwind_Resume
LEHE1:
L13:
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
LFE979:
	.def	___gxx_personality_v0;	.scl	2;	.type	32;	.endef
	.section	.gcc_except_table,"w"
LLSDA979:
	.byte	0xff
	.byte	0xff
	.byte	0x1
	.uleb128 LLSDACSE979-LLSDACSB979
LLSDACSB979:
	.uleb128 LEHB0-LFB979
	.uleb128 LEHE0-LEHB0
	.uleb128 L12-LFB979
	.uleb128 0
	.uleb128 LEHB1-LFB979
	.uleb128 LEHE1-LEHB1
	.uleb128 0
	.uleb128 0
LLSDACSE979:
	.text
	.globl	__ZTV9CoolClass
	.section	.rdata$_ZTV9CoolClass,"dr"
	.linkonce same_size
	.align 8
__ZTV9CoolClass:
	.long	0
	.long	__ZTI9CoolClass
	.long	__ZN9CoolClass3setEi
	.long	__ZN9CoolClass3getEv
	.globl	__ZTV4Base
	.section	.rdata$_ZTV4Base,"dr"
	.linkonce same_size
	.align 8
__ZTV4Base:
	.long	0
	.long	__ZTI4Base
	.long	___cxa_pure_virtual
	.long	___cxa_pure_virtual
	.globl	__ZTS9CoolClass
	.section	.rdata$_ZTS9CoolClass,"dr"
	.linkonce same_size
__ZTS9CoolClass:
	.ascii "9CoolClass\0"
	.globl	__ZTI9CoolClass
	.section	.rdata$_ZTI9CoolClass,"dr"
	.linkonce same_size
	.align 4
__ZTI9CoolClass:
	.long	__ZTVN10__cxxabiv120__si_class_type_infoE+8
	.long	__ZTS9CoolClass
	.long	__ZTI4Base
	.globl	__ZTS4Base
	.section	.rdata$_ZTS4Base,"dr"
	.linkonce same_size
__ZTS4Base:
	.ascii "4Base\0"
	.globl	__ZTI4Base
	.section	.rdata$_ZTI4Base,"dr"
	.linkonce same_size
	.align 4
__ZTI4Base:
	.long	__ZTVN10__cxxabiv117__class_type_infoE+8
	.long	__ZTS4Base
	.text
	.def	___tcf_0;	.scl	3;	.type	32;	.endef
___tcf_0:
LFB987:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 8
	mov	ecx, OFFSET FLAT:__ZStL8__ioinit
	call	__ZNSt8ios_base4InitD1Ev
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE987:
	.def	__Z41__static_initialization_and_destruction_0ii;	.scl	3;	.type	32;	.endef
__Z41__static_initialization_and_destruction_0ii:
LFB986:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 24
	cmp	DWORD PTR [ebp+8], 1
	jne	L15
	cmp	DWORD PTR [ebp+12], 65535
	jne	L15
	mov	ecx, OFFSET FLAT:__ZStL8__ioinit
	call	__ZNSt8ios_base4InitC1Ev
	mov	DWORD PTR [esp], OFFSET FLAT:___tcf_0
	call	_atexit
L15:
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE986:
	.def	__GLOBAL__sub_I_main;	.scl	3;	.type	32;	.endef
__GLOBAL__sub_I_main:
LFB988:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	sub	esp, 24
	mov	DWORD PTR [esp+4], 65535
	mov	DWORD PTR [esp], 1
	call	__Z41__static_initialization_and_destruction_0ii
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE988:
	.section	.ctors,"w"
	.align 4
	.long	__GLOBAL__sub_I_main
	.section .rdata,"dr"
	.align 8
LC1:
	.long	0
	.long	1078296576
	.ident	"GCC: (GNU) 4.8.1"
	.def	__Znwj;	.scl	2;	.type	32;	.endef
	.def	__Unwind_Resume;	.scl	2;	.type	32;	.endef
	.def	___cxa_pure_virtual;	.scl	2;	.type	32;	.endef
	.def	__ZNSt8ios_base4InitD1Ev;	.scl	2;	.type	32;	.endef
	.def	__ZNSt8ios_base4InitC1Ev;	.scl	2;	.type	32;	.endef
	.def	_atexit;	.scl	2;	.type	32;	.endef
