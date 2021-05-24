package com.soumyajit.arch_playground

import com.soumyajit.staticfeature.StaticFeatureDepsProvider

interface DepsProvider :
    AppDepsProvider,
    StaticFeatureDepsProvider