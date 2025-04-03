# Fluxus i18n - Programmer's Guide

## Overview

Fluxus i18n is a lightweight internationalization (i18n) library for the Fluxus UI framework, providing simple yet powerful translation capabilities for Scala.js applications. It seamlessly integrates with Fluxus's reactive component model and leverages Airstream signals for state management.

## Core Features

- **YAML-based translations**: Define translations in structured YAML format
- **Reactive language switching**: Language changes automatically re-render components
- **Parameter substitution**: Insert dynamic content into translation strings
- **Nested translation keys**: Access translations using dot notation
- **Type-safe APIs**: Full integration with Scala's type system
- **Hook-based integration**: Simple and consistent API for components

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
```

```yaml
# French translations
greeting: Bonjour
farewell: Au revoir
user:
  welcome: "Bienvenue, {name}!"
```

### 2. Load translations in your application

Load translations at application startup and set the default language:

```scala
import io.github.edadma.fluxus.i18n.I18n

// Load translations 
I18n.loadTranslation("en", enYamlString)
I18n.loadTranslation("fr", frYamlString)

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
      "Switch to French"
    )
  )
}
```

## API Reference

### I18n Object

The main entry point for managing translations.

#### Loading Translations

```scala
// Load translations from YAML string
I18n.loadTranslation(lang: String, yamlString: String): Unit
```

This method:
- Parses the YAML string
- Flattens nested structures into dot-notation keys
- Stores translations for the specified language
- Logs success or failure information

Example:
```scala
val enYaml = """
greeting: Hello
user:
  welcome: "Welcome, {name}!"
"""

I18n.loadTranslation("en", enYaml)
```

#### Managing Languages

```scala
// Set the current language
I18n.setLanguage(lang: String): Unit

// Get the current language (direct access)
val currentLang = I18n.currentLanguage.now()
```

The `setLanguage` method:
- Changes the current language if the specified language exists in loaded translations
- Logs warnings if the language hasn't been loaded
- Updates the `currentLanguage` signal, triggering re-renders in components using translations

#### Accessing Translation Signals

For advanced use cases, you can directly access the reactive signals:

```scala
// The current language signal (Var[String])
val langSignal = I18n.currentLanguage

// The current translations signal (Signal[Map[String, String]])
val translationsSignal = I18n.translations
```

### Translation Hook

The primary way to use translations in components.

```scala
// Get translation function
val t = useTranslation()

// Use in component
div(t("greeting"))
```

#### Translation Function

The `useTranslation` hook returns a `TranslationFunction` with the following API:

```scala
// Simple translation lookup
t("key")

// Translation with parameter substitution
t("key.with.params", "param1" -> "value1", "param2" -> "value2")
```

The translation function:
- Looks up the key in the current language's translations
- Falls back to the key itself if no translation is found
- Substitutes parameters in the format `{name}` with provided values

## Advanced Usage

### Nested Keys

YAML supports nested structures which are flattened to dot notation:

```yaml
user:
  profile:
    title: "User Profile"
    subtitle: "Edit your information"
```

These can be accessed using dot notation:

```scala
t("user.profile.title") // "User Profile"
```

### Parameter Substitution

Parameters allow inserting dynamic content into translations:

```yaml
cart:
  items: "You have {count} items in your cart"
  total: "Total: ${amount}"
```

```scala
t("cart.items", "count" -> itemCount.toString)
t("cart.total", "amount" -> formatPrice(total))
```

### Language Switching Components

Create components that allow users to switch languages:

```scala
def LanguageSwitcher = () => {
  val currentLang = useSignal(I18n.currentLanguage)
  
  div(
    cls := "language-switcher",
    button(
      cls := s"lang-btn ${if (currentLang == "en") "active" else ""}",
      onClick := (() => I18n.setLanguage("en")),
      "English"
    ),
    button(
      cls := s"lang-btn ${if (currentLang == "fr") "active" else ""}",
      onClick := (() => I18n.setLanguage("fr")),
      "Français"
    )
  )
}
```

### Integrating with Existing Signals

Use `useSignal` to integrate with other reactive data:

```scala
def NotificationComponent = () => {
  val t = useTranslation()
  val notificationCount = useSignal(notificationSignal)
  
  div(
    cls := "notification",
    if (notificationCount > 0)
      span(t("notifications.count", "count" -> notificationCount.toString))
    else
      span(t("notifications.empty"))
  )
}
```

### Loading Translations from External Sources

You can load translations from various sources:

```scala
// From server API
def loadTranslations(): Unit = {
  val languages = List("en", "fr", "es", "de")
  
  languages.foreach { lang =>
    fetch(s"/api/translations/$lang")
      .then(response => response.text())
      .then(text => I18n.loadTranslation(lang, text))
  }
}

// From local storage
def loadCachedTranslations(): Unit = {
  val cachedLang = localStorage.getItem("preferredLanguage")
  
  if (cachedLang != null) {
    I18n.setLanguage(cachedLang)
  }
}
```

### Showing Loading States

Handle translation loading states:

```scala
def TranslatedApp = () => {
  val (translationsLoaded, setTranslationsLoaded, _) = useState(false)
  val t = useTranslation()
  
  useEffect(
    () => {
      // Load translations
      loadTranslationsAsync().foreach { _ =>
        setTranslationsLoaded(true)
      }
      ()
    },
    Seq()
  )
  
  div(
    if (!translationsLoaded)
      div("Loading translations...")
    else
      div(
        h1(t("app.title")),
        MainContent <> ()
      )
  )
}
```

## Best Practices

### Translation Key Organization

Organize translation keys logically:

```yaml
# Good organization
app:
  title: "My Application"
  subtitle: "A great app"

user:
  profile:
    title: "User Profile"
  settings:
    title: "User Settings"

errors:
  notFound: "Page not found"
  serverError: "Server error"
```

Use consistent naming conventions:
- Use camelCase for keys
- Group related translations under common prefixes
- Keep nesting to 2-3 levels for maintainability

### Translation File Management

Split large translation files by feature:

```
/translations
  /en
    common.yaml
    user.yaml
    products.yaml
  /fr
    common.yaml
    user.yaml
    products.yaml
```

Then load and merge them:

```scala
def loadTranslationsForLanguage(lang: String): Unit = {
  val files = List("common", "user", "products")
  
  files.foreach { file =>
    fetch(s"/translations/$lang/$file.yaml")
      .then(response => response.text())
      .then(text => I18n.loadTranslation(lang, text))
  }
}
```

### Pluralization and Complex Formatting

For pluralization, use parameters with conditionals in the UI:

```scala
def MessageCount = () => {
  val t = useTranslation()
  val count = useSignal(messageCountSignal)
  
  p(
    if (count == 0)
      t("messages.none")
    else if (count == 1)
      t("messages.one")
    else
      t("messages.many", "count" -> count.toString)
  )
}
```

### Fallback Strategy

Implement a fallback strategy for missing translations:

```scala
def enhancedTranslate(key: String, params: (String, String)*): String = {
  val t = useTranslation()
  val result = t(key, params: _*)
  
  // Check if translation was found (result equals key)
  if (result == key && !key.contains(".")) {
    console.warn(s"Missing translation for key: $key")
    "** $key **" // Mark missing translations visually
  } else {
    result
  }
}
```

### Testing Translations

Create a component to visualize and test all translations:

```scala
def TranslationDebugger = () => {
  val currentLang = useSignal(I18n.currentLanguage)
  val allTranslations = useSignal(I18n.translations)
  
  div(
    cls := "translation-debugger",
    h2(s"Translations for $currentLang"),
    ul(
      allTranslations.toSeq.sortBy(_._1).map { case (key, value) =>
        li(
          span(cls := "key", key),
          span(cls := "value", value)
        )
      }
    )
  )
}
```

## Common Patterns

### User Language Preference

Detect and store user language preference:

```scala
def initializeLanguage(): Unit = {
  // Try to get from localStorage
  val storedLang = Option(dom.window.localStorage.getItem("preferredLanguage"))
  
  // Fall back to browser language
  val browserLang = Option(dom.window.navigator.language).map(_.split("-")(0))
  
  // Fall back to default
  val language = storedLang.orElse(browserLang).getOrElse("en")
  
  // Check if we have translations for this language
  if (I18n.translationData.contains(language)) {
    I18n.setLanguage(language)
  } else {
    I18n.setLanguage("en") // Default fallback
  }
}

// Store preference when changed
def onLanguageChange(lang: String): Unit = {
  I18n.setLanguage(lang)
  dom.window.localStorage.setItem("preferredLanguage", lang)
}
```

### SEO-friendly Translated Routes

Create a system for translated routes:

```scala
object Routes {
  // Define route translations
  val routeTranslations = Map(
    "en" -> Map(
      "home" -> "",
      "about" -> "about",
      "contact" -> "contact"
    ),
    "fr" -> Map(
      "home" -> "",
      "about" -> "a-propos",
      "contact" -> "contactez-nous"
    )
  )
  
  // Get translated route
  def getRoute(routeKey: String): String = {
    val lang = I18n.currentLanguage.now()
    val translations = routeTranslations.getOrElse(lang, routeTranslations("en"))
    translations.getOrElse(routeKey, routeKey)
  }
  
  // Parse route to get key
  def parseRoute(path: String): String = {
    val lang = I18n.currentLanguage.now()
    val translations = routeTranslations.getOrElse(lang, routeTranslations("en"))
    translations.find(_._2 == path).map(_._1).getOrElse("notFound")
  }
}
```

### Dynamic Language Loading

Load languages on-demand:

```scala
def useDynamicLanguage(lang: String): (Boolean, String) => Unit = {
  val (loaded, setLoaded, _) = useState(I18n.translationData.contains(lang))
  
  // Function to load language if needed
  def loadLanguageIfNeeded(activate: Boolean): Unit = {
    if (!loaded) {
      fetch(s"/api/translations/$lang")
        .then(response => response.text())
        .then(text => {
          I18n.loadTranslation(lang, text)
          setLoaded(true)
          if (activate) I18n.setLanguage(lang)
        })
    } else if (activate) {
      I18n.setLanguage(lang)
    }
  }
  
  (loaded, loadLanguageIfNeeded)
}

// Usage in a component
def LanguageSelector = () => {
  val currentLang = useSignal(I18n.currentLanguage)
  val (frLoaded, loadFrench) = useDynamicLanguage("fr")
  
  div(
    button(
      cls := s"lang-btn ${if (currentLang == "en") "active" else ""}",
      onClick := (() => I18n.setLanguage("en")),
      "English"
    ),
    button(
      cls := s"lang-btn ${if (currentLang == "fr") "active" else ""}",
      onClick := (() => loadFrench(true)),
      s"Français ${if (!frLoaded) "(Loading...)" else ""}"
    )
  )
}
```

## Internal Architecture

Understanding the internals can help with debugging and extension.

### TranslationFunction Implementation

The `TranslationFunction` class provides parameter substitution:

```scala
class TranslationFunction(translations: Map[String, String]) {
  def apply(key: String, params: (String, String)*): String = {
    val template = translations.getOrElse(key, key)

    // Apply parameter substitutions
    params.foldLeft(template) { case (text, (name, value)) =>
      text.replace(s"{$name}", value)
    }
  }
}
```

### Flattening Nested Translations

YAML structures are flattened to dot notation:

```scala
private def flattenTranslations(obj: Map[String, Any], prefix: String = ""): Map[String, String] = {
  obj.flatMap {
    case (key, value: String) => Map(s"$prefix$key" -> value)
    case (key, value: Map[_, _]) =>
      flattenTranslations(value.asInstanceOf[Map[String, Any]], s"$prefix$key.")
    case (key, value) =>
      // Convert numbers, booleans etc. to strings
      Map(s"$prefix$key" -> value.toString)
  }
}
```

### Signal-based Reactivity

Language changes are reactive through Airstream signals:

```scala
// The current language signal
val currentLanguage: Var[String] = Var[String]("en")

// Signal derived from current language that provides the current translations
val translations: Signal[Map[String, String]] =
  currentLanguage.signal.map(lang => translationData.getOrElse(lang, Map.empty))
```

## Troubleshooting

### Common Issues and Solutions

#### 1. Missing Translations

**Symptoms**:
- Translation keys appear in the UI instead of translated text
- Console warnings about missing translations

**Solutions**:
- Check that the language has been loaded correctly
- Verify the key paths and nesting levels
- Add a debugging component to display all loaded translations
- Implement a visual indicator for missing translations in development

#### 2. Language Not Changing

**Symptoms**:
- UI not updating after language change
- Language indicator shows new language but text doesn't change

**Solutions**:
- Verify `I18n.setLanguage` is being called with the correct language code
- Check that translations for the language have been loaded
- Ensure components are using the `useTranslation` hook
- Check console for warnings about unavailable languages

#### 3. Parameter Substitution Not Working

**Symptoms**:
- `{paramName}` appears in the UI instead of the substituted value

**Solutions**:
- Verify parameter names match exactly (case-sensitive)
- Check that parameter values are being converted to strings
- Ensure the translation contains the parameter placeholder in correct format

#### 4. YAML Parsing Errors

**Symptoms**:
- Translations not loading
- Console errors about invalid YAML

**Solutions**:
- Validate YAML format using online tools
- Check for common YAML issues like indentation and special characters
- Put strings with special characters in quotes
- Check console logs for specific error messages

### Debugging Tools

#### Logging Translation Data

```scala
def logTranslationData(): Unit = {
  console.log("Available languages:", I18n.translationData.keys.mkString(", "))
  
  I18n.translationData.foreach { case (lang, translations) =>
    console.log(s"$lang translations:", translations)
  }
}
```

#### Creating a Translation Inspector Component

```scala
def TranslationInspector = () => {
  val currentLang = useSignal(I18n.currentLanguage)
  val (searchQuery, setSearchQuery, _) = useState("")
  
  val filteredTranslations = I18n.translationData
    .getOrElse(currentLang, Map.empty)
    .filter { case (key, value) => 
      key.contains(searchQuery) || value.contains(searchQuery)
    }
  
  div(
    cls := "translation-inspector",
    h2("Translation Inspector"),
    div(
      label("Language: "),
      select(
        value := currentLang,
        onChange := ((e: dom.Event) => {
          I18n.setLanguage(e.target.asInstanceOf[dom.html.Select].value)
        }),
        I18n.translationData.keys.toSeq.map(lang =>
          option(value := lang, lang)
        )
      )
    ),
    div(
      label("Search: "),
      input(
        typ := "text",
        value := searchQuery,
        onInput := ((e: dom.Event) => 
          setSearchQuery(e.target.asInstanceOf[dom.html.Input].value)
        )
      )
    ),
    table(
      thead(
        tr(
          th("Key"),
          th("Translation")
        )
      ),
      tbody(
        filteredTranslations.toSeq.sortBy(_._1).map { case (key, value) =>
          tr(
            td(key),
            td(value)
          )
        }
      )
    )
  )
}
```

## Conclusion

The Fluxus i18n library provides a simple yet powerful solution for adding internationalization to Fluxus applications. By leveraging Airstream signals for reactive state management and integrating with Fluxus's component model through hooks, it offers a clean and elegant API for handling translations.

The YAML-based translation format is easy to work with and the dot notation access pattern makes organizing translations intuitive. Parameter substitution provides flexibility for dynamic content, making it suitable for a wide range of applications.

For most use cases, the basic API of loading translations, switching languages, and using the `useTranslation` hook will be sufficient. For more complex scenarios, the library's architecture allows for extension and customization through direct access to the underlying signals and translation data.