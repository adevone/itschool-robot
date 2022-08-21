@file:Suppress("SpellCheckingInspection")

package io.adev.itschool.robot.levels

import io.adev.itschool.robot.common.arena.entity.arena.parseArena

// @formatter:off

val demoArena = parseArena("""
ppppp
pr  p
ppptp
ppppp
""".trim())

val arena1 = parseArena("""
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

val homework1Variant1Arena = parseArena("""
pppppppppppppppppp
ppppp        ppppp
pppp         ppppp
p    ppppppp  pppp
p    p     p  pppp
ppr ppppppp tppppp
""".trim())

val homework1Variant2Arena = parseArena("""
ppppppppppppppppppp
ppp     pppp  pp tp
pp  ppp  ppp  p  pp
p  ppppp  pp    ppp
p  ppppp        ppp
pp  ppp  ppp  p  pp
ppp rp  pppp  pp  p
ppppppppppppppppppp
""".trim())

val homework1Variant3Arena = parseArena("""
pppppppppppppppppppppppppp
prppppppp            ppppp
p   ppp   ppppppppp    ppp
ppp  p  pppppppppppptp   p
pppp   ppppppppppppppppp p
ppp  p  pppppppppppppp   p
p   ppp   pppppppppp   ppp
p ppppppp            ppppp
pppppppppppppppppppppppppp
""".trim())

val arena3 = parseArena("""
ppppp
pr *p
ppptp
ppppp
""".trim())

val homework2Variant1Arena = parseArena("""
pppppppppppppppppp
pppppp      pppppp
pppp  pppppp  pppp
ppp pppppppppp ppp
ppp pppppppppp ppp
pp pppppppppppp pp
pp pppppppppppp pp
p pppppppppppppp p
p ppr   pp    pp p
p p     pp     p p
pp p   * pp   p pp
ppp pppp  pppp ppp
pppp pp   ppp pppp
pppp pp*ppppp pppp
pppp pp pp pp pppp
pppppt       ppppp
pppppppppppppppppp
""".trim())

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

val arena5 = parseArena("""
ppppp
pr #p
ppptp
ppppp
""".trim())

val arena6 = parseArena("""
pppppppppp
r        p
pppppp#ppp
p   p  ppp
p#p   pppp
p  pppp tp
pp   #   p
pppppppppp
""".trim())

val homework3DenisArena = parseArena("""
     pppppppppppppppppppp
     p        p  p#       p
     p   p         pp p   p pp
pppppp       p    p  ppppppp  p
p    p            p           p
 ppppp     p   p #   #     #   p
     p#pp#   p   p       p   # p
   pppr   p       p  ppppppp#tp
  p   pppppppppppp ppppppppppp
  pppp  pppp      pppp  pppp
""".trimIndent())

val homework3EduardArena = parseArena("""
pppp
  rpp
     p
      p
      p
       p
       ppp
 p###pp   p
 p    p    p
p      p p p
p      p   p
p  p   pppp
 p    #    p
  pppp     p
     p  pppp
     pp#    p
    p        p
   p         p
  p           p
 p p         p
 p p#####pppp
 p       p
  p    tp
""".trimIndent())

val arena7 = parseArena("""
ppppppppppp
ptppppppprp
p  ppppp  p
pp  ppp  pp
ppp  p  ppp
p    p  c p
p ppppppp p
p#ppppppp p
p pppppppvp
pv #  c   p
pp ppppp pp
ppppppppppp
""".trimIndent())

val homework4EduardArena = parseArena("""
 ppppppppppp
p    t     p
pppppp     p         ppppppppppp
     p     p        p           p
     p#####p    pppp             p
     p     #pppp                  ppp
     p     # p                       pp
     p     # p                        pp
      pppppp v    c                   pp
           pppp ppp                 pp p
              p p pppppppppppvpppppp   p
             p p  p p       p p    p   p
             p p   p p      p p  p p   p
            p p    p p     p  p  p  p     
            p p    p p    p  c    prp
           ppp      ppp   ppp     ppp
           ppp      ppp   ppp     ppp
""".trimIndent())

val homework4DenisArena = parseArena("""
             ppppppppppp
   ppppp pppp           pppppp
 pp   vtpp  #                ppppp
pp     vp p  #                   pp
 pp    ppp   #                    pp
  p       pp #         p    ppppp p p
  pp    pp#  #        p     v    p  pp
    p  c   ppp p     p    pppp  cp  pp
     ppp   p  p  p  p ppppp p pp p
       pppp    p  p  p    p p  prp
                pp pp     pp   pp
               pppppp    ppp  ppp
""".trimIndent())

val arena8 = parseArena("""
ppppppppp
ppp%%%ppp
ppp%p%ppp
p%%%p%%%p
p%ppppp%p
p%%%  ptp
ppp%p ppp
ppp%p ppp
ppr%p  pp
""".trim())
