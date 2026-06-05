{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = [
    pkgs.maven
    pkgs.jdk8
  ];

  shellHook = ''
    export JAVA_HOME=${pkgs.jdk8}
  '';
}
