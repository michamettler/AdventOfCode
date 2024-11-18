$file = Get-Content .\input.txt
$x = 0
$y = 0
$aim = 0
for ($i = 0; $i -lt $file.count; $i++)
{ 
    if ($file[$i] -match "forward")
    {
        [int]$x= $x + [int]$file[$i].Substring($file[$i].Length-1)
        [int]$y= $y + ([int]$file[$i].Substring($file[$i].Length-1)*$aim)
    }
    if ($file[$i] -match "down")
    {
        [int]$aim= $aim + [int]$file[$i].Substring($file[$i].Length-1)
    }
    if ($file[$i] -match "up")
    {
        [int]$aim= $aim - [int]$file[$i].Substring($file[$i].Length-1)
    }
}
$solution = $x*$y
$solution
