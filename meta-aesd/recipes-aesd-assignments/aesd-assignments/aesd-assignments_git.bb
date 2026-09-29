# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# TODO: Set this  with the path to your assignments rep.  Use ssh protocol and see lecture notes
# about how to setup ssh-agent for passwordless access
# SRC_URI = "git://git@github.com/cu-ecen-aeld/<your assignments repo>;protocol=ssh;branch=master"

PV = "1.0+git${SRCPV}"
# TODO: set to reference a specific commit hash in your assignment repo
#SRCREV = "f99b82a5d4cb2a22810104f89d4126f52f4dfaba"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/server"

SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-KaywebDev;protocol=https;branch=main"
SRCREV = "25f58a396fa9b0910220795e7748f4ebdae97e01"
inherit update-rc.d

INITSCRIPT_NAME = "aesdsocket"
INITSCRIPT_PARAMS = "defaults 90"

FILES:${PN} += "${sysconfdir}/init.d/aesdsocket ${bindir}/aesdsocket"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
		install -d ${D}${bindir} ${D}${sysconfdir}/init.d
		install -m 0755 ${B}/aesdsocket ${D}${bindir}/aesdsocket
		install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket
}
