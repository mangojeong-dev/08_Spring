param(
    [Parameter(Mandatory = $true)]
    [ValidatePattern('^[A-Za-z0-9][A-Za-z0-9._-]*$')]
    [string]$ProjectName,

    [Parameter(Mandatory = $true)]
    [ValidatePattern('^[a-z_][a-z0-9_]*(\.[a-z_][a-z0-9_]*)+$')]
    [string]$BasePackage,

    [string]$OutputDirectory = (Split-Path -Parent $PSScriptRoot)
)

$ErrorActionPreference = 'Stop'
$repositoryRoot = Split-Path -Parent $PSScriptRoot
$templateRoot = Join-Path $repositoryRoot 'spring-legacy-template'
$sourceProject = Join-Path $repositoryRoot 'SpringLegacy'
$targetRoot = Join-Path $OutputDirectory $ProjectName

if (Test-Path -LiteralPath $targetRoot) {
    throw "Target already exists: $targetRoot"
}

$basePackagePath = $BasePackage.Replace('.', [IO.Path]::DirectorySeparatorChar)
$databaseName = ($ProjectName -replace '[^A-Za-z0-9_]', '_') + '_db'

Get-ChildItem -LiteralPath $templateRoot -Recurse -File |
    Where-Object { $_.Name -ne 'README.md' } |
    ForEach-Object {
        $relativePath = $_.FullName.Substring($templateRoot.Length + 1)
        $relativePath = $relativePath.Replace('__BASE_PACKAGE_PATH__', $basePackagePath)
        if ($relativePath.EndsWith('.tpl')) {
            $relativePath = $relativePath.Substring(0, $relativePath.Length - 4)
        }

        $destination = Join-Path $targetRoot $relativePath
        $destinationDirectory = Split-Path -Parent $destination
        New-Item -ItemType Directory -Path $destinationDirectory -Force | Out-Null

        $content = Get-Content -LiteralPath $_.FullName -Raw -Encoding UTF8
        $content = $content.Replace('__PROJECT_NAME__', $ProjectName)
        $content = $content.Replace('__BASE_PACKAGE__', $BasePackage)
        $content = $content.Replace('__PROJECT_NAME_DB__', $databaseName)
        [IO.File]::WriteAllText(
            $destination,
            $content,
            [Text.UTF8Encoding]::new($false))
    }

Copy-Item -LiteralPath (Join-Path $sourceProject 'gradlew') -Destination $targetRoot
Copy-Item -LiteralPath (Join-Path $sourceProject 'gradlew.bat') -Destination $targetRoot
Copy-Item -LiteralPath (Join-Path $sourceProject 'gradle') -Destination $targetRoot -Recurse

Write-Host "Created Spring Legacy project: $targetRoot"
