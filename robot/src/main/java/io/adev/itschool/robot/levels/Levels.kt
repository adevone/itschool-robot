package io.adev.itschool.robot.levels

import io.adev.itschool.robot.common.arena.entity.arena.parseArena

@Suppress("SpellCheckingInspection")
val demoArena = parseArena("""
ppppp
pr  p
ppptp
ppppp
""".trim())

@Suppress("SpellCheckingInspection")
val arena1 = parseArena("""
pppppppppp
r        p
pppppp ppp
p   p  ppp
p p   pppp
p  pppp tp
pp       p
pppppppppp
""".trim())

@Suppress("SpellCheckingInspection")
val arena2 = parseArena("""
pppppppppp
r        p
pppppp ppp
p   p  ppp
p p   pppp
p  pppp tp
pp       p
pppppppppp
""".trim())

@Suppress("SpellCheckingInspection")
val homework1DenisArena = parseArena("""
ppppppppp
ppp   ppp
ppp p ppp
p   p   p
p ppppp p
p     ptp
ppp p ppp
ppp p ppp
ppr p  pp
""".trim())

@Suppress("SpellCheckingInspection")
val homework1EdwardArena = parseArena("""
pppppppppppppppppp
ppppp        ppppp
pppp         ppppp
p    ppppppp  pppp
p    p     p  pppp
ppr ppppppp tppppp
""".trim())

@Suppress("SpellCheckingInspection")
val arena3 = parseArena("""
ppppp
pr *p
ppptp
ppppp
""".trim())

@Suppress("SpellCheckingInspection")
val arena4 = parseArena("""
pppppppppp
r        p
pppppp*ppp
p   p  ppp
p*p   pppp
p  pppp tp
pp   *   p
pppppppppp
""".trim())