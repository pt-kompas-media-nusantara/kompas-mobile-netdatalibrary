#
#  Be sure to run `pod spec lint NetDataLibrary.podspec' to ensure this is a
#  valid spec and to remove all comments including this before submitting the spec.
#
#  To learn more about Podspec attributes see https://guides.cocoapods.org/syntax/podspec.html
#  To see working Podspecs in the CocoaPods repo see https://github.com/CocoaPods/Specs/
#

Pod::Spec.new do |spec|

  # ―――  Spec Metadata  ―――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  #
  #  These will help people to find your library, and whilst it
  #  can feel like a chore to fill in it's definitely to your advantage. The
  #  summary should be tweet-length, and the description more in depth.
  #

  spec.name         = "NetDataLibrary"
  spec.version      = "1.0.51"
  spec.summary      = "Kompas.id"

  # This description is used to generate tags and improve search results.
  #   * Think: What does it do? Why did you write it? What is the focus?
  #   * Try to keep it short, snappy and to the point.
  #   * Write the description between the DESC delimiters below.
  #   * Finally, don't worry about the indent, CocoaPods strips it!
  spec.description  = <<-DESC
  Pustaka ini membantu Anda melakukan hal-hal keren di proyek iOS Anda.
                   DESC

  spec.homepage     = "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary"
  # spec.screenshots  = "www.example.com/screenshots_1.gif", "www.example.com/screenshots_2.gif"


  # ―――  Spec License  ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  #
  #  Licensing your code is important. See https://choosealicense.com for more info.
  #  CocoaPods will detect a license file if there is a named LICENSE*
  #  Popular ones are 'MIT', 'BSD' and 'Apache License, Version 2.0'.
  #

  spec.license      = { :type => "MIT", :file => "LICENSE" }


  # ――― Author Metadata  ――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  #
  #  Specify the authors of the library, with email addresses. Email addresses
  #  of the authors are extracted from the SCM log. E.g. $ git log. CocoaPods also
  #  accepts just a name if you'd rather not provide an email address.
  #
  #  Specify a social_media_url where others can refer to, for example a twitter
  #  profile URL.
  #

  spec.author             = { "nurirppan" => "nurirppan@gmail.com" }
  # Or just: spec.author    = "nurirppan"
  # spec.authors            = { "nurirppan" => "nurirppan@gmail.com" }
  # spec.social_media_url   = "https://twitter.com/nurirppan"

  # ――― Platform Specifics ――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  #
  #  If this Pod runs only on iOS or OS X, then specify the platform and
  #  the deployment target. You can optionally include the target after the platform.
  #

  # spec.platform     = :ios
  # spec.platform     = :ios, "5.0"

  #  When using multiple platforms
  spec.ios.deployment_target = "16.0"
  # spec.osx.deployment_target = "10.7"
  # spec.watchos.deployment_target = "2.0"
  # spec.tvos.deployment_target = "9.0"
  # spec.visionos.deployment_target = "1.0"


  # ――― Source Location ―――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  #
  #  Specify the location from where the source should be retrieved.
  #  Supports git, hg, bzr, svn and HTTP.
  #

  spec.source       = { :git => "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary.git", :tag => "#{spec.version}" }


  # ――― Source Code ―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  #
  #  CocoaPods is smart about how it includes source code. For source files
  #  giving a folder will include any swift, h, m, mm, c & cpp files.
  #  For header files it will include any header in the folder.
  #  Not including the public_header_files will make all headers public.
  #

  # spec.source_files  = "Classes", "Classes/**/*.{h,m,swift}"
  # spec.source_files  = "shared/**/*.{h,m,swift}", "iosApp/**/*.{h,m,swift}" # file tersebar ke beberapa folder
  # spec.source_files  = "shared/**/*.{h,m,swift}"
  spec.source_files = "shared/src/commonMain/kotlin/**/*.{kt,kotlin}"
  # spec.exclude_files = "shared/Exclude"
  # spec.exclude_files = "Classes/Exclude"

  # spec.public_header_files = "Classes/**/*.h"


  # ――― Resources ―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  #
  #  A list of resources included with the Pod. These are copied into the
  #  target bundle with a build phase script. Anything else will be cleaned.
  #  You can preserve files from being cleaned, please don't preserve
  #  non-essential files like tests, examples and documentation.
  #

  # spec.resource  = "icon.png"
  # spec.resources = "Resources/*.png"

  # spec.preserve_paths = "FilesToSave", "MoreFilesToSave"


  # ――― Project Linking ―――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  #
  #  Link your library with frameworks, or libraries. Libraries do not include
  #  the lib prefix of their name.
  #

  # spec.framework  = "SomeFramework"
  # spec.frameworks = "SomeFramework", "AnotherFramework"

  # spec.library   = "iconv"
  # spec.libraries = "iconv", "xml2"


  # ――― Project Settings ――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  #
  #  If your library depends on compiler flags you can set them in the xcconfig hash
  #  where they will only apply to your library. If you depend on other Podspecs
  #  you can include multiple dependencies to ensure it works.

  # spec.requires_arc = true

  # spec.xcconfig = { "HEADER_SEARCH_PATHS" => "$(SDKROOT)/usr/include/libxml2" }
  # spec.dependency "JSONKit", "~> 1.4"

  # ――― Framework built by Kotlin Multiplatform - Public Pods ――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  # spec.vendored_frameworks = "build/c"
  # spec.vendored_frameworks = "build/bin/ios/releaseFramework/NetDataLibrary.xcframework"
  # spec.vendored_frameworks = "shared/build/XCFrameworks/release/Shared.xcframework"

  # pod spec create NetDataLibrary
  # pod spec lint NetDataLibrary.podspec
  # pod trunk push NetDataLibrary.podspec
  # pod trunk register nurirppan@gmail.com 'nurirppan'

  # spec.prepare_command = <<-SCRIPT
  # ./gradlew :shared:assembleReleaseXCFramework
  # SCRIPT

# ――― Private Pods ――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
  # https://guides.cocoapods.org/making/private-cocoapods.html
  # pod repo add NetDataLibrary https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary.git

  # To check if your installation is successful and ready to go:
  # cd ~/.cocoapods/repos/REPO_NAME
  # pod repo lint .

  # pod repo push NetDataLibrary NetDataLibrary.podspec

# ――― Remove Public Pods ――――――――――――――――――――――――――――――――――――――――――――――――――――――――― #
# pod trunk login
# pod trunk delete NetDataLibrary 1.0.44
# pod trunk delete NetDataLibrary --silent


end
