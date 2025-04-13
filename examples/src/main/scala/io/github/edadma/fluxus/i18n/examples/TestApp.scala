package io.github.edadma.fluxus.i18n.examples

import io.github.edadma.fluxus._
import io.github.edadma.fluxus.i18n._
import io.github.edadma.fluxus.i18n.examples.I18nData
import org.scalajs.dom

// Main application component
def TestApp: FluxusNode = {
  // Initialize translations on application startup
  initializeTranslations()

  // Main component
  div(
    cls := "container mx-auto p-4",
    h1(cls := "text-2xl font-bold mb-4", "Translation Test App"),

    // Language selector
    LanguageSwitcher <> (),

    // Display some translations
    TranslationDisplay <> (),
  )
}

// Initialize translations from I18nData
def initializeTranslations(): Unit = {
  // Load all available translations
  I18n.loadTranslation("en", I18nData.enTranslations)
  I18n.loadTranslation("fr", I18nData.frTranslations)
  I18n.loadTranslation("es", I18nData.esTranslations)
  I18n.loadTranslation("de", I18nData.deTranslations)

  // Set default language to English
  I18n.setLanguage("en")

  // Log available languages
  val availableLanguages = I18n.translationData.keys.mkString(", ")
  println(s"Available languages: $availableLanguages")

  // Log the number of translation keys for each language
  I18n.translationData.foreach { case (lang, translations) =>
    println(s"$lang: ${translations.size} keys")
  }
}

// Language switcher component
val LanguageSwitcher = () => {
  val currentLang = useSignal(I18n.currentLanguage)

  div(
    cls := "mb-4 p-4 bg-gray-100 rounded",
    h2(cls := "text-lg font-semibold mb-2", "Select Language:"),
    div(
      cls := "flex space-x-2",
      button(
        cls     := s"px-3 py-1 rounded ${if (currentLang == "en") "bg-blue-500 text-white" else "bg-gray-200"}",
        onClick := (() => I18n.setLanguage("en")),
        "English",
      ),
      button(
        cls     := s"px-3 py-1 rounded ${if (currentLang == "fr") "bg-blue-500 text-white" else "bg-gray-200"}",
        onClick := (() => I18n.setLanguage("fr")),
        "Français",
      ),
      button(
        cls     := s"px-3 py-1 rounded ${if (currentLang == "es") "bg-blue-500 text-white" else "bg-gray-200"}",
        onClick := (() => I18n.setLanguage("es")),
        "Español",
      ),
      button(
        cls     := s"px-3 py-1 rounded ${if (currentLang == "de") "bg-blue-500 text-white" else "bg-gray-200"}",
        onClick := (() => I18n.setLanguage("de")),
        "Deutsch",
      ),
    ),
    p(
      cls := "mt-2",
      s"Current language: $currentLang",
    ),
  )
}

// Component to display various translations
val TranslationDisplay = () => {
  val t = useTranslation()

  div(
    cls := "space-y-4",

    // App title
    section(
      cls := "bg-white p-4 shadow rounded",
      h2(cls := "text-xl font-bold mb-2", "App Title:"),
      p(cls  := "text-lg font-semibold text-blue-600", t("app.title")),
    ),

    // Categories
    section(
      cls := "bg-white p-4 shadow rounded",
      h2(cls := "text-xl font-bold mb-2", "Categories:"),
      ul(
        cls := "list-disc pl-5",
        li(t("categories.length")),
        li(t("categories.weight")),
        li(t("categories.volume")),
        li(t("categories.temperature")),
      ),
    ),

    // Converter interface labels
    section(
      cls := "bg-white p-4 shadow rounded",
      h2(cls := "text-xl font-bold mb-2", "Converter Interface:"),
      div(
        cls := "grid grid-cols-2 gap-2",
        div(
          p(cls := "font-semibold", t("converter.from") + ":"),
          p(cls := "font-semibold", t("converter.to") + ":"),
        ),
        div(
          p(t("converter.swap")),
          p(t("converter.convert")),
        ),
      ),

      // Example with parameter substitution
      div(
        cls := "mt-3 p-3 bg-gray-100 rounded",
        h3(cls := "font-semibold", "Example with parameters:"),
        p(
          t("converter.title", "from" -> t("units.meter"), "to" -> t("units.foot")),
        ),
        p(
          t(
            "converter.result",
            "value"  -> "5",
            "from"   -> t("units.meters"),
            "result" -> "16.4",
            "to"     -> t("units.feet"),
          ),
        ),
      ),
    ),

    // Footer section
    section(
      cls := "bg-white p-4 shadow rounded",
      h2(cls := "text-xl font-bold mb-2", "Footer:"),
      p(t("footer.copyright")),
      div(
        cls := "flex space-x-4 mt-2",
        a(href := "#", t("footer.privacy")),
        a(href := "#", t("footer.about")),
        a(href := "#", t("footer.contact")),
      ),
    ),

    // Debug section
    section(
      cls := "mt-6 p-4 bg-red-50 border border-red-200 rounded",
      h2(cls := "text-xl font-bold mb-2 text-red-700", "Debug Info:"),
      p(cls  := "font-mono text-sm", "Raw translated keys (to check if translations are loaded):"),
      pre(
        cls := "bg-gray-900 text-white p-2 rounded overflow-auto text-xs mt-2",
        "app.title: " + t("app.title") + "\n" +
          "categories.length: " + t("categories.length") + "\n" +
          "converter.from: " + t("converter.from") + "\n" +
          "units.meter: " + t("units.meter") + "\n" +
          "footer.copyright: " + t("footer.copyright"),
      ),
    ),
  )
}
//
//// Entry point
//@main
//def main(): Unit = {
//  // Create a container for the app if it doesn't exist
//  val appContainer =
//    Option(dom.document.getElementById("app"))
//      .getOrElse {
//        val container = dom.document.createElement("div")
//        container.id = "app"
//        dom.document.body.appendChild(container)
//        container
//      }
//
//  // Render the application
//  render(App, appContainer)
//}
