{ pkgs }: {
  deps = [
    pkgs.openjdk8
    pkgs.maven
    pkgs.sqlite
    pkgs.unzip
  ];
}
