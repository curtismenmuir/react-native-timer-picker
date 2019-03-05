
Pod::Spec.new do |s|
  s.name         = "RNTimerPicker"
  s.version      = "1.0.0"
  s.summary      = "RNTimerPicker"
  s.description  = <<-DESC
                  RNTimerPicker
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNTimerPicker.git", :tag => "master" }
  s.source_files  = "RNTimerPicker/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  