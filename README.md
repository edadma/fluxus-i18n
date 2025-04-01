# Fluxus I18n

[![License: ISC](https://img.shields.io/badge/License-ISC-blue.svg)](https://opensource.org/licenses/ISC)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.edadma/fluxus-i18n_sjs1_3.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.edadma/fluxus-i18n_sjs1_3)

A lightweight internationalization (i18n) library for the Fluxus UI framework, providing simple yet powerful translation capabilities for your Scala.js applications.

## Features

- Simple integration with Fluxus components
- YAML-based translation files
- Reactive language switching with Airstream signals
- Parameter substitution for dynamic content
- Nested translation keys with dot notation
- Type-safe translation function

## Installation

Add the following dependency to your `build.sbt`:

```scala
libraryDependencies += "io.github.edadma" %%% "fluxus-i18n" % "0.0.1"
```

## Quick Start

### 1. Define your translations in YAML

Create translation files for each language:

```yaml
# English translations
greeting: Hello
farewell: Goodbye
user:
  welcome: "Welcome, {name}!"
  profile: "User Profile"
```

```yaml
# French translations
greeting: Bonjour
farewell: Au revoir
user:
  welcome: "Bienvenue, {name}!"
  profile: "Profil d'utilisateur"
```

### 2. Load translations in your application

```scala
import io.github.edadma.fluxus.i18n.I18n

// Load translations at application startup
I18n.loadTranslation("en", enYaml)
I18n.loadTranslation("fr", frYaml)

// Set default language
I18n.setLanguage("en")
```

### 3. Use translations in your components

```scala
import io.github.edadma.fluxus._
import io.github.edadma.fluxus.i18n._

def GreetingComponent = () => {
  // Get translation function
  val t = useTranslation()
  
  div(
    h1(t("greeting")),
    p(t("user.welcome", "name" -> "John")),
    button(
      onClick := (() => I18n.setLanguage("fr")),
      t("changeLanguage")
    )
  )
}
```

## API Reference

### Loading Translations

```scala
// Load translations from YAML string
I18n.loadTranslation(lang: String, yamlString: String): Unit
```

### Changing Languages

```scala
// Set the current language
I18n.setLanguage(lang: String): Unit

// Get the current language signal (from Airstream)
val langSignal = I18n.currentLanguage.signal
```

### Using Translations in Components

```scala
// Hook to use in components
val t = useTranslation()

// Simple translation
t("key")

// Translation with parameter substitution
t("key.with.params", "param1" -> "value1", "param2" -> "value2")
```

## Advanced Usage

### Accessing Translation State Directly

You can access the reactive state of translations using Airstream signals:

```scala
import io.github.edadma.fluxus.useSignal

def LanguageSwitcherComponent = () => {
  // Current language as a reactive value
  val currentLang = useSignal(I18n.currentLanguage)
  
  div(
    p(s"Current language: $currentLang"),
    button(
      onClick := (() => I18n.setLanguage("en")),
      "English"
    ),
    button(
      onClick := (() => I18n.setLanguage("fr")),
      "Français"
    )
  )
}
```

### Dynamic Content with Parameters

```scala
// Translation definition (in YAML):
// user:
//   messages: "You have {count} new {count|message:messages}"

// In component:
val t = useTranslation()
t("user.messages", "count" -> messageCount.toString)
```


## Requirements

- Scala 3.6.x
- Scala.js 1.18.x
- Fluxus 0.0.8+

## License

ISC License - see LICENSE file for details.
