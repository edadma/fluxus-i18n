package io.github.edadma.fluxus.i18n.examples

object I18nData {
  // English translations
  val enTranslations = """
app:
  title: Universal Unit Converter
  categories: Categories
  show: Show
  hide: Hide

categories:
  length: Length & Distance
  weight: Weight & Mass
  volume: Volume & Capacity
  temperature: Temperature
  area: Area
  speed: Speed
  time: Time
  data: Data Storage
  fuel: Fuel Economy

converter:
  title: "{from} to {to} Converter"
  enterValue: Enter Value
  from: From
  to: To
  swap: Swap
  convert: Convert
  copy: Copy
  clear: Clear
  result: "{value} {from} = {result} {to}"

buttons:
  swap: Swap
  convert: Convert
  copy: Copy
  clear: Clear

sections:
  popular: Popular Conversions
  formula: Conversion Formula
  commonConversions: Common Conversions
  allConverters: "All {category} Converters"

formulas:
  toFormula: "To convert from {from} to {to}:"
  fromFormula: "To convert from {to} to {from}:"
  formula: "{to} = {from} × {factor}"
  inverseFormula: "{from} = {to} × {factor}"

units:
  # Length
  meter: meter
  meters: meters
  foot: foot
  feet: feet
  inch: inch
  inches: inches
  centimeter: centimeter
  centimeters: centimeters
  kilometer: kilometer
  kilometers: kilometers
  mile: mile
  miles: miles
  yard: yard
  yards: yards
  millimeter: millimeter
  millimeters: millimeters
  micrometer: micrometer
  micrometers: micrometers
  nanometer: nanometer
  nanometers: nanometers
  lightYear: light year
  lightYears: light years

  # Weight
  kilogram: kilogram
  kilograms: kilograms
  pound: pound
  pounds: pounds
  gram: gram
  grams: grams
  ounce: ounce
  ounces: ounces
  tonne: tonne
  tonnes: tonnes
  ton: ton
  tons: tons
  milligram: milligram
  milligrams: milligrams
  grain: grain
  grains: grains
  stone: stone
  stones: stones

  # Volume
  liter: liter
  liters: liters
  gallon: gallon
  gallons: gallons
  milliliter: milliliter
  milliliters: milliliters
  fluidOunce: fluid ounce
  fluidOunces: fluid ounces
  cubicMeter: cubic meter
  cubicMeters: cubic meters
  cubicFoot: cubic foot
  cubicFeet: cubic feet
  cup: cup
  cups: cups
  pint: pint
  pints: pints

  # Temperature
  celsius: Celsius
  fahrenheit: Fahrenheit
  kelvin: Kelvin
  rankine: Rankine

  # Area
  squareMeter: square meter
  squareMeters: square meters
  squareFoot: square foot
  squareFeet: square feet
  acre: acre
  acres: acres
  hectare: hectare
  hectares: hectares
  squareMile: square mile
  squareMiles: square miles
  squareKilometer: square kilometer
  squareKilometers: square kilometers
  squareInch: square inch
  squareInches: square inches
  squareCentimeter: square centimeter
  squareCentimeters: square centimeters

  # Speed
  kilometersPerHour: kilometers per hour
  milesPerHour: miles per hour
  metersPerSecond: meters per second
  feetPerSecond: feet per second
  knot: knot
  knots: knots
  mach: Mach

  # Time
  second: second
  seconds: seconds
  minute: minute
  minutes: minutes
  hour: hour
  hours: hours
  day: day
  days: days
  week: week
  weeks: weeks
  year: year
  years: years
  millisecond: millisecond
  milliseconds: milliseconds

  # Data
  byte: byte
  bytes: bytes
  bit: bit
  bits: bits
  kilobyte: kilobyte
  kilobytes: kilobytes
  megabyte: megabyte
  megabytes: megabytes
  gigabyte: gigabyte
  gigabytes: gigabytes
  terabyte: terabyte
  terabytes: terabytes
  megabit: megabit
  megabits: megabits
  gigabit: gigabit
  gigabits: gigabits

  # Fuel Economy
  milesPerGallon: miles per gallon
  litersPerHundredKm: liters per 100km
  milesPerGallonUK: miles per gallon (UK)
  kilometersPerLiter: kilometers per liter

footer:
  copyright: © 2025 Universal Unit Converter
  privacy: Privacy Policy
  about: About
  contact: Contact
"""

  // French translations
  val frTranslations = """
app:
  title: "Convertisseur Universel d'Unités"
  categories: Catégories
  show: Afficher
  hide: Masquer

categories:
  length: Longueur & Distance
  weight: Poids & Masse
  volume: Volume & Capacité
  temperature: Température
  area: Surface
  speed: Vitesse
  time: Temps
  data: Stockage de Données
  fuel: Économie de Carburant

converter:
  title: "Convertisseur de {from} en {to}"
  enterValue: Entrer une Valeur
  from: De
  to: Vers
  swap: Échanger
  convert: Convertir
  copy: Copier
  clear: Effacer
  result: "{value} {from} = {result} {to}"

buttons:
  swap: Échanger
  convert: Convertir
  copy: Copier
  clear: Effacer

sections:
  popular: Conversions Populaires
  formula: Formule de Conversion
  commonConversions: Conversions Communes
  allConverters: "Tous les Convertisseurs de {category}"

formulas:
  toFormula: "Pour convertir de {from} en {to} :"
  fromFormula: "Pour convertir de {to} en {from} :"
  formula: "{to} = {from} × {factor}"
  inverseFormula: "{from} = {to} × {factor}"

units:
  # Length
  meter: mètre
  meters: mètres
  foot: pied
  feet: pieds
  inch: pouce
  inches: pouces
  centimeter: centimètre
  centimeters: centimètres
  kilometer: kilomètre
  kilometers: kilomètres
  mile: mile
  miles: miles
  yard: yard
  yards: yards
  millimeter: millimètre
  millimeters: millimètres
  micrometer: micromètre
  micrometers: micromètres
  nanometer: nanomètre
  nanometers: nanomètres
  lightYear: année-lumière
  lightYears: années-lumière

  # Weight
  kilogram: kilogramme
  kilograms: kilogrammes
  pound: livre
  pounds: livres
  gram: gramme
  grams: grammes
  ounce: once
  ounces: onces
  tonne: tonne
  tonnes: tonnes
  ton: tonne américaine
  tons: tonnes américaines
  milligram: milligramme
  milligrams: milligrammes
  grain: grain
  grains: grains
  stone: stone
  stones: stones

footer:
  copyright: © 2025 Convertisseur Universel d'Unités
  privacy: Politique de Confidentialité
  about: À Propos
  contact: Contact
"""

  // Spanish translations
  val esTranslations = """
app:
  title: Conversor Universal de Unidades
  categories: Categorías
  show: Mostrar
  hide: Ocultar

categories:
  length: Longitud y Distancia
  weight: Peso y Masa
  volume: Volumen y Capacidad
  temperature: Temperatura
  area: Área
  speed: Velocidad
  time: Tiempo
  data: Almacenamiento de Datos
  fuel: Economía de Combustible

converter:
  title: "Conversor de {from} a {to}"
  enterValue: Introducir Valor
  from: Desde
  to: A
  swap: Intercambiar
  convert: Convertir
  copy: Copiar
  clear: Borrar
  result: "{value} {from} = {result} {to}"

buttons:
  swap: Intercambiar
  convert: Convertir
  copy: Copiar
  clear: Borrar

sections:
  popular: Conversiones Populares
  formula: Fórmula de Conversión
  commonConversions: Conversiones Comunes
  allConverters: "Todos los Conversores de {category}"

formulas:
  toFormula: "Para convertir de {from} a {to}:"
  fromFormula: "Para convertir de {to} a {from}:"
  formula: "{to} = {from} × {factor}"
  inverseFormula: "{from} = {to} × {factor}"

units:
  meter: metro
  meters: metros
  foot: pie
  feet: pies
  inch: pulgada
  inches: pulgadas
  centimeter: centímetro
  centimeters: centímetros
  kilometer: kilómetro
  kilometers: kilómetros
  mile: milla
  miles: millas
  yard: yarda
  yards: yardas
  millimeter: milímetro
  millimeters: milímetros
  micrometer: micrómetro
  micrometers: micrómetros
  nanometer: nanómetro
  nanometers: nanómetros
  lightYear: año luz
  lightYears: años luz

  # Weight
  kilogram: kilogramo
  kilograms: kilogramos
  pound: libra
  pounds: libras
  gram: gramo
  grams: gramos
  ounce: onza
  ounces: onzas
  tonne: tonelada
  tonnes: toneladas
  ton: tonelada americana
  tons: toneladas americanas
  milligram: miligramo
  milligrams: miligramos
  grain: grano
  grains: granos
  stone: stone
  stones: stones

footer:
  copyright: © 2025 Conversor Universal de Unidades
  privacy: Política de Privacidad
  about: Acerca de
  contact: Contacto
"""

  // German translations
  val deTranslations = """
app:
  title: Universeller Einheitenumrechner
  categories: Kategorien
  show: Anzeigen
  hide: Ausblenden

categories:
  length: Länge & Entfernung
  weight: Gewicht & Masse
  volume: Volumen & Kapazität
  temperature: Temperatur
  area: Fläche
  speed: Geschwindigkeit
  time: Zeit
  data: Datenspeicherung
  fuel: Kraftstoffverbrauch

converter:
  title: "{from} zu {to} Umrechner"
  enterValue: Wert eingeben
  from: Von
  to: Nach
  swap: Tauschen
  convert: Umrechnen
  copy: Kopieren
  clear: Löschen
  result: "{value} {from} = {result} {to}"

buttons:
  swap: Tauschen
  convert: Umrechnen
  copy: Kopieren
  clear: Löschen

sections:
  popular: Beliebte Umrechnungen
  formula: Umrechnungsformel
  commonConversions: Häufige Umrechnungen
  allConverters: "Alle {category} Umrechner"

formulas:
  toFormula: "Um von {from} zu {to} umzurechnen:"
  fromFormula: "Um von {to} zu {from} umzurechnen:"
  formula: "{to} = {from} × {factor}"
  inverseFormula: "{from} = {to} × {factor}"

units:
  meter: Meter
  meters: Meter
  foot: Fuß
  feet: Fuß
  inch: Zoll
  inches: Zoll
  centimeter: Zentimeter
  centimeters: Zentimeter
  kilometer: Kilometer
  kilometers: Kilometer
  mile: Meile
  miles: Meilen
  yard: Yard
  yards: Yards
  millimeter: Millimeter
  millimeters: Millimeter
  micrometer: Mikrometer
  micrometers: Mikrometer
  nanometer: Nanometer
  nanometers: Nanometer
  lightYear: Lichtjahr
  lightYears: Lichtjahre

  # Weight
  kilogram: Kilogramm
  kilograms: Kilogramm
  pound: Pfund
  pounds: Pfund
  gram: Gramm
  grams: Gramm
  ounce: Unze
  ounces: Unzen
  tonne: Tonne
  tonnes: Tonnen
  ton: US-Tonne
  tons: US-Tonnen
  milligram: Milligramm
  milligrams: Milligramm
  grain: Gran
  grains: Gran
  stone: Stone
  stones: Stone

footer:
  copyright: © 2025 Universeller Einheitenumrechner
  privacy: Datenschutzrichtlinie
  about: Über uns
  contact: Kontakt
"""
}
