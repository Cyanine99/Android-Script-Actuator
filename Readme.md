# ScriptActuator Readme

[TOC]

## 1.  Brief Introduction

This simple test sequence runner executes instructions according to your script file.  The runner is intended to perform GUI  related tests, not functional tests.

Implementions are mostly based on UiAutomator. If you are interested in UiAutomator, see https://developer.android.google.cn/training/testing/ui-automator .

## 2. How to Use

You need:

- Gradle version: >= 3.4.0
- JDK version: >= 1.7
- Android SDK version: >= 18

All programme contents are under AndroidTest root folder. To start your test, you need:

1. write a script file (e.g. script.txt) and put the file under *assets* folder **of the application you want to test with**;
2. set execution parameters according to your need in *Configuration.class*
3. start test

## 3. Script Format

The script file MUST be written in given format.

### Format Requirements

- Each line starts with an uppercase written KEYWORD
- Each word is separated by a single space
- Package name MUST be given (by keyword PKG)

### Supported Keywords

- PKG package name
- ACT entry activity name
- EVE an event, followed by event type and attached component name(if exists)
- END end mark

### Supported Events

| Event Type | Param1                                          | Param2       |
| ---------- | ----------------------------------------------- | ------------ |
| click      | component_resource_id                           |              |
| area-click | x                                               | y            |
| input      | component_resource_id                           |              |
| input      | component_resource_id                           | text_content |
| long-click | component_resource_id                           |              |
| back       |                                                 |              |
| home       |                                                 |              |
| menu       |                                                 |              |
| rotate     |                                                 |              |
| rotate     | orientation  (0 = left, 1 = natural, 2 = right) |              |

### Example

PKG com.e.example

ACT EntryActivity

EVE click start

EVE back

EVE area-click 1800 90

EVE rotate 0

EVE rotate 2

EVE back

END

## 4. Info & Contact Me

- Current Version: 1.0.0
- Contact me on GitHub or Mail: 1767943521@qq.com

